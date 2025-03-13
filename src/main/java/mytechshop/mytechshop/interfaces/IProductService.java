package mytechshop.mytechshop.interfaces;

import mytechshop.mytechshop.models.Brand;
import mytechshop.mytechshop.models.Category;
import mytechshop.mytechshop.models.Color;
import mytechshop.mytechshop.requests.CreateProductRequest;
import mytechshop.mytechshop.requests.UpdateProductRequest;
import mytechshop.mytechshop.models.Product;
import java.util.List;

public interface IProductService {
    Product createProduct(CreateProductRequest createProductRequest);
    Product updateProduct(Long id, UpdateProductRequest updateProductRequest);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryId(long categoryId);
    List<Product> getProductsByBrandId(long brandId); // Added this method
    List<Product> getProductsByBrand(Brand brand);
    List<Product> getProductsByColor(Color color);
    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);
    List<Product> searchProductsByName(String name);
    List<Product> getAvailableProducts();
    void deleteProduct(Long id);
}