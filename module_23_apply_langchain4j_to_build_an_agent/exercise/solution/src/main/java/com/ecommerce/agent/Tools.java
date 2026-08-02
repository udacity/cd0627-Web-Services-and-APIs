package com.ecommerce.agent;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class Tools {

    @Tool("Check flight availability from source to destination")
    public String checkFlight(String source, String destination) {
        return "Flight available for " + source + " to " + destination;
    }

    @Tool("Get current weather for a city")
    public String getWeather(String city) {
        return "Sunny in " + city;
    }
}
