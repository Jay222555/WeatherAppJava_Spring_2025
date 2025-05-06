import java.awt.*;

public class MainController {
    @FXML private TextField zipCodeField;
    @FXML private Label temperatureLabel;
    @FXML private Label locationLabel;
    @FXML private Label windLabel;
    @FXML private Label errorLabel;

    @FXML
    private void handleGetWeather() {
        String zip = zipCodeField.getText().trim();
        errorLabel.setText("");

        try {
            LocationInfo location = LocationService.getLocationByZip(zip);
            WeatherData weather = WeatherService.getWeather(location);

            locationLabel.setText(weather.getLocation());
            temperatureLabel.setText(String.format("%.1f°F", weather.getTemperature()));
            windLabel.setText(String.format("%.1f mph, %s",
                    weather.getWindSpeed(),
                    getCompassDirection(weather.getWindDirection())));
        } catch (Exception e) {
            errorLabel.setText("Error: " + e.getMessage());
        }
    }

    private String getCompassDirection(double angle) {
        String[] directions = {"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest", "North"};
        return directions[(int)Math.round(((angle % 360) / 45))];
    }
}

