import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ZipCodeToLatLong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter zip code: ");
        String zipCode = scanner.next();

        try {
            // Step 1: Get lat/lon from ZIP code
            String zipApiUrl = "https://zippopotam.us/us/" + zipCode;
            URL zipUrl = new URL(zipApiUrl);
            HttpURLConnection zipConn = (HttpURLConnection) zipUrl.openConnection();
            zipConn.setRequestMethod("GET");

            int zipResponseCode = zipConn.getResponseCode();
            if (zipResponseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(zipConn.getInputStream()));
                StringBuilder zipContent = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    zipContent.append(inputLine);
                }
                in.close();
                zipConn.disconnect();

                JSONObject jsonResponse = new JSONObject(zipContent.toString());
                JSONObject place = jsonResponse.getJSONArray("places").getJSONObject(0);

                String latitude = place.getString("latitude");
                String longitude = place.getString("longitude");
                String state = place.getString("state");
                String stateAbv = place.getString("state abbreviation");
                String city = place.getString("place name");

                System.out.println("City: " + city);
                System.out.println("State: " + state + " (" + stateAbv + ")");
                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);

                // Step 2: Use lat/lon in Open-Meteo API
                String weatherUrl = String.format(
                        "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current=temperature_2m,wind_speed_10m,wind_direction_10m",
                        latitude, longitude
                );
                URL weatherApiUrl = new URL(weatherUrl);
                HttpURLConnection weatherConn = (HttpURLConnection) weatherApiUrl.openConnection();
                weatherConn.setRequestMethod("GET");

                int weatherResponseCode = weatherConn.getResponseCode();
                if (weatherResponseCode == 200) {
                    BufferedReader weatherIn = new BufferedReader(new InputStreamReader(weatherConn.getInputStream()));
                    StringBuilder weatherContent = new StringBuilder();
                    String weatherLine;
                    while ((weatherLine = weatherIn.readLine()) != null) {
                        weatherContent.append(weatherLine);
                    }
                    weatherIn.close();
                    weatherConn.disconnect();

                    JSONObject weatherJson = new JSONObject(weatherContent.toString());
                    JSONObject currentWeather = weatherJson.getJSONObject("current");

                    double temperature = currentWeather.getDouble("temperature_2m");
                    double windSpeed = currentWeather.getDouble("wind_speed_10m");
                    double windDirection = currentWeather.getDouble("wind_direction_10m");

                    System.out.println("\n--- Current Weather ---");
                    System.out.println("Temperature: " + temperature + "°C");
                    System.out.println("Wind Speed: " + windSpeed + " m/s");
                    System.out.println("Wind Direction: " + windDirection + "°");
                } else {
                    System.out.println("Error: Unable to fetch weather data.");
                }
            } else {
                System.out.println("Error: Invalid ZIP code or unable to fetch location data.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
