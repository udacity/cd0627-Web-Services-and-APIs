package com.ecommerce.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final List<Product> products = new CopyOnWriteArrayList<>(
            IntStream.rangeClosed(1, 10)
                    .mapToObj(i -> new Product((long) i, "Product " + i, 10.0 * i, 100L + i))
                    .collect(Collectors.toList())
    );

    @QueryMapping
    public List<Product> products() {
        return products;
    }

    @MutationMapping
    public Product createProduct(@Argument String name, @Argument double price) {
        Product p = new Product((long) (products.size() + 1), name, price, 999L);
        products.add(p);
        return p;
    }

    // Step 3: Creating the N+1 Problem
    @SchemaMapping(typeName = "Product", field = "supplier")
    public Supplier supplier(Product product) {
        log.info("Fetching supplier for product ID: {}", product.id());
        // Simulating a database fetch
        return new Supplier(product.supplierId(), "Supplier " + product.supplierId());
    }
}
