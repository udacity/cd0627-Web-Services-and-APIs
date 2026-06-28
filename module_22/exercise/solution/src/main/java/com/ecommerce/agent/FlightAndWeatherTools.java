package com.ecommerce.agent;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class FlightAndWeatherTools {

    @Tool("Check the weather for a given city")
    public String checkWeather(String city) {
        return "72 degrees and sunny in " + city;
    }

    @Tool("Search for flights from an origin city to a destination city")
    public String searchFlights(String origin, String destination) {
        if ("Atlantis".equalsIgnoreCase(destination)) {
            throw new RuntimeException("Flight API is currently down");
        }
        return "Flight DL123 available from " + origin + " to " + destination;
    }
}
