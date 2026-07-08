package com.ecommerce.agent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import java.util.function.Function;

@Configuration
public class Tools {

    @Bean
    @Description("Check flight availability from source to destination")
    public Function<FlightRequest, String> checkFlight() {
        return req -> "Flight available for " + req.source() + " to " + req.destination();
    }

    @Bean
    @Description("Get current weather for a city")
    public Function<WeatherRequest, String> getWeather() {
        return req -> "Sunny in " + req.city();
    }

    public record FlightRequest(String source, String destination) {}
    public record WeatherRequest(String city) {}
}