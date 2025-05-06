import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationService {
    public static LocationInfo getLocationByZip(String zipCode) throws Exception {
        String url = "https://zippopotam.us/us/" + zipCode;
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) json.append(line);
            reader.close();

            JSONObject response = new JSONObject(json.toString());
            JSONObject place = response.getJSONArray("places").getJSONObject(0);

            return new LocationInfo(
                    place.getString("place name"),
                    place.getString("state"),
                    Double.parseDouble(place.getString("latitude")),
                    Double.parseDouble(place.getString("longitude"))
            );
        } else {
            throw new Exception("Invalid ZIP code or API error.");
        }
    }
}

