package com.ecommerce.product.repository;

import com.ecommerce.product.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory product store used for the demo.
 *
 * <p>A {@link ConcurrentHashMap} replaces a real database so we can run the
 * demo with zero infrastructure. It is seeded with three sample products on
 * startup so that {@code GET /products} and {@code GET /products/{id}} return
 * meaningful data from the moment the app starts.
 *
 * <p><b>Thread safety:</b> {@link ConcurrentHashMap} and {@link AtomicLong}
 * guarantee safe concurrent access; Spring beans are singletons by default.
 */
@Repository
public class ProductRepository {

    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public ProductRepository() {
        // Seed data – shown to students during the demo so GET / works immediately
        save(new Product(idSequence.getAndIncrement(), "Wireless Headphones",
                "Over-ear, noise-cancelling, 30-hour battery", new BigDecimal("149.99")));
        save(new Product(idSequence.getAndIncrement(), "Mechanical Keyboard",
                "Tenkeyless, Cherry MX Brown switches", new BigDecimal("89.99")));
        save(new Product(idSequence.getAndIncrement(), "USB-C Hub",
                "7-in-1: HDMI, 3× USB-A, SD, PD 100 W", new BigDecimal("39.99")));
    }

    /** Returns all products. */
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    /** Returns the product with the given id, or empty if not found. */
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * Persists a new product, assigning a server-generated id.
     *
     * @param name        product name
     * @param description short description
     * @param price       unit price
     * @return the newly created {@link Product} with its assigned id
     */
    public Product create(String name, String description, BigDecimal price) {
        long newId = idSequence.getAndIncrement();
        Product product = new Product(newId, name, description, price);
        store.put(newId, product);
        return product;
    }

    /**
     * Applies a partial update to an existing product.
     *
     * @param id          id of the product to update
     * @param name        new name (null = no change)
     * @param description new description (null = no change)
     * @param price       new price (null = no change)
     * @return the updated product, or empty if the product does not exist
     */
    public Optional<Product> patch(long id, String name, String description, BigDecimal price) {
        return findById(id).map(existing -> {
            Product updated = new Product(
                    existing.id(),
                    name        != null ? name        : existing.name(),
                    description != null ? description : existing.description(),
                    price       != null ? price       : existing.price()
            );
            store.put(id, updated);
            return updated;
        });
    }

    // ---- package-private helper used only by the seeding constructor ----

    private void save(Product p) {
        store.put(p.id(), p);
    }
}
