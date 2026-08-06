package com.ecommerce.ai;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateTools {

    @Tool(description = "Get today's date")
    public String getCurrentDate() {
        return LocalDate.now().toString();
    }
}
