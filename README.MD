# 🌟 Java Final Project – Spring 2025

This JavaFX application demonstrates the integration of **two APIs**:  
✅ [Zippopotam.us](https://api.zippopotam.us) – to convert a U.S. ZIP code into latitude and longitude  
✅ [Open-Meteo](https://open-meteo.com/) – to retrieve weather information based on coordinates

The project showcases working with **JSON data**, **API chaining**, and building a functional **JavaFX user interface**.

---

## 🎥 Video Demonstration

![Screen recording](images/screen-recording.gif)

---

## 📋 Project Overview

The goal of this project is to create a JavaFX app that allows users to enter a U.S. ZIP code, fetches the corresponding latitude and longitude using the Zippopotam.us API, then uses those coordinates to retrieve and display weather data from Open-Meteo.

You’ll get hands-on experience with:
- Consuming multiple APIs
- Parsing JSON data
- Building a user-friendly GUI with JavaFX
- Input validation using regular expressions
- Structuring a Java application with clean code practices

This project starts with a **console-based UI** to validate API integration and core logic, then moves into a JavaFX graphical interface.

---

## ✅ Features

- Console-based app for initial testing
- JavaFX graphical interface with:
    - ZIP code input field (with validation using regex)
    - 'Fetch Weather' button
    - Display area showing:
        - Temperature
        - Wind speed
        - Weather condition (clear, cloudy, etc.)
- Error handling for invalid input and network errors
- API chaining: using output from one API as input for another

### 🎯 Bonus Features (Optional):
- Toggle between **Celsius** and **Fahrenheit**
- Display additional data (humidity, pressure, weather icon)
- Save user's previous ZIP code inputs

---

## 🚀 How to Run the Application

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Jay222555/WeatherAppJava_Spring_2025.git
