package mytechshop.mytechshop.interfaces;

import java.util.List;
import java.util.Optional;
import mytechshop.mytechshop.models.*;

public interface IProductService {
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Category category);
    List<Product> getProductsByBrand(Brand brand);
    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);
    List<Product> searchProductsByName(String name);
    List<Product> getAvailableProducts();
    void deleteProduct(Long id);
}
