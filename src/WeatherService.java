import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    public static WeatherData getWeather(LocationInfo location) throws Exception {
        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%.4f&longitude=%.4f&current=temperature_2m,wind_speed_10m,wind_direction_10m&temperature_unit=fahrenheit&wind_speed_unit=mph",
                location.latitude, location.longitude
        );

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) json.append(line);
            reader.close();

            JSONObject response = new JSONObject(json.toString());
            JSONObject current = response.getJSONObject("current");

            return new WeatherData(
                    location.city + ", " + location.state,
                    current.getDouble("temperature_2m"),
                    "N/A", // Open-Meteo doesn't give weather description; you can use another API if needed.
                    current.getDouble("wind_direction_10m"),
                    current.getDouble("wind_speed_10m")
            );
        } else {
            throw new Exception("Failed to retrieve weather data.");
        }
    }
}

