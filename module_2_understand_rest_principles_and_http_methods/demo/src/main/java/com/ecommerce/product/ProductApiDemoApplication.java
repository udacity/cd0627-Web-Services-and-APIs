package com.ecommerce.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Module 2 instructor demo.
 *
 * <p>Run with:
 * <pre>
 *   ./mvnw spring-boot:run          (from the module_2/demo directory)
 * </pre>
 * The API is then available at http://localhost:8080/products
 */
@SpringBootApplication
public class ProductApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiDemoApplication.class, args);
    }
}
