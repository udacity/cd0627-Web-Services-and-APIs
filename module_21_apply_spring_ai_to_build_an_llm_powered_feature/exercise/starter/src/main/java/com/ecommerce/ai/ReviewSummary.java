package com.ecommerce.ai;

import java.util.List;

public record ReviewSummary(String title, List<String> bulletPoints, String sentiment) {
}
