package mytechshop.mytechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query method to find products by category
    List<Product> findByCategory(Category category);

    // Custom query method to find products by name
    List<Product> findByNameContainingIgnoreCase(String name);
}