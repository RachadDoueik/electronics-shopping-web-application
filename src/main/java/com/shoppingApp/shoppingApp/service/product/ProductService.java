package com.shoppingApp.shoppingApp.service.product;
import com.shoppingApp.shoppingApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import com.shoppingApp.shoppingApp.model.Product;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductService implements IProductService {

    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return List.of();
    }

    @Override
    public List<Product> getProductByBrand(String brandName) {
        return List.of();
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String categoryName, String brandName) {
        return List.of();
    }

    @Override
    public List<Product> getProductByName(String productName) {
        return List.of();
    }

    @Override
    public List<Product> getProductByBrandAndName(String brandName, String productName) {
        return List.of();
    }

    @Override
    public Long getProductCountByBrandAndName(String brandName, String productName) {
        return 0L;
    }
}
