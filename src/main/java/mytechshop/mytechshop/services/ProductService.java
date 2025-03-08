package mytechshop.mytechshop.services;

import lombok.RequiredArgsConstructor;
import mytechshop.mytechshop.interfaces.IProductService;
import mytechshop.mytechshop.requests.CreateProductRequest;
import mytechshop.mytechshop.requests.UpdateProductRequest;
import mytechshop.mytechshop.models.*;
import mytechshop.mytechshop.repositories.BrandRepository;
import mytechshop.mytechshop.repositories.CategoryRepository;
import mytechshop.mytechshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Override
    public Product createProduct(@Valid CreateProductRequest createProductRequest) {
        // Fetch category and brand
        Category category = categoryRepository.findById(createProductRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Brand brand = brandRepository.findById(createProductRequest.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        // Create product entity
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .description(createProductRequest.getDescription())
                .price(createProductRequest.getPrice())
                .stock(createProductRequest.getStock())
                .category(category)
                .brand(brand)
                .build();

        // Save and return product
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, @Valid UpdateProductRequest updateProductRequest) {
        // Fetch existing product
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Fetch category and brand
        Category category = categoryRepository.findById(updateProductRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Brand brand = brandRepository.findById(updateProductRequest.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        // Update product fields
        product.setName(updateProductRequest.getName());
        product.setDescription(updateProductRequest.getDescription());
        product.setPrice(updateProductRequest.getPrice());
        product.setStock(updateProductRequest.getStock());
        product.setCategory(category);
        product.setBrand(brand);

        // Save and return updated product
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> getAvailableProducts() {
        return productRepository.findByStockGreaterThan(0);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
