package com.shoppingApp.shoppingApp.service.product;
import com.shoppingApp.shoppingApp.model.Product;
import java.util.List;

public interface IProductService {

    Product addProduct(Product product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    void updateProduct(Product product , Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String categoryName);

    List<Product> getProductByBrand(String brandName);

    List<Product> getProductByCategoryAndBrand(String categoryName, String brandName);

    List<Product> getProductByName(String productName);

    List<Product> getProductByBrandAndName(String brandName, String productName);

    Long getProductCountByBrandAndName(String brandName, String productName);

}
