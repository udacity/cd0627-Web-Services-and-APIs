package com.ecommerce.agent;

import org.springframework.stereotype.Component;

@Component
public class Tools {
    // TODO (Step 6): Annotate with @Tool and provide a description
    public String checkFlight(String source, String destination) {
        return "Flight available for " + source + " to " + destination;
    }

    // TODO (Step 7): Annotate with @Tool and provide a description
    public String getWeather(String city) {
        return "Sunny in " + city;
    }
}
