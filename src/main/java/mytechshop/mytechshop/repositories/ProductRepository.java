package mytechshop.mytechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Fetch products by category ID
    List<Product> findByCategoryId(Long categoryId);

    // Fetch products by brand ID
    List<Product> findByBrandId(Long brandId);

    // Fetch products by brand Object
    List<Product> findByBrand(Brand brand);

    // Fetch products within a price range
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Search products by name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Fetch products with stock greater than a specified value
    List<Product> findByStockGreaterThan(Integer stock);

    //Fecth products by color object
    List<Product> findByColor(Color color);
}