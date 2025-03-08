package mytechshop.mytechshop.services;

import lombok.RequiredArgsConstructor;
import mytechshop.mytechshop.interfaces.IProductService;
import mytechshop.mytechshop.requests.CreateProductRequest;
import mytechshop.mytechshop.requests.UpdateProductRequest;
import mytechshop.mytechshop.models.*;
import mytechshop.mytechshop.repositories.ImageRepository;
import mytechshop.mytechshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Override
    public Product createProduct(@Valid CreateProductRequest createProductRequest) {
        // Create product entity
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        product.setStock(createProductRequest.getStock());
        product.setCategory(createProductRequest.getCategory()); // Set category directly
        product.setBrand(createProductRequest.getBrand()); // Set brand directly

        // Save product
        Product savedProduct = productRepository.save(product);

        // Add images to the product
        if (createProductRequest.getImageUrls() != null && !createProductRequest.getImageUrls().isEmpty()) {
            List<Image> images = createProductRequest.getImageUrls().stream()
                    .map(url -> {
                        Image image = new Image();
                        image.setImageUrl(url);
                        image.setProduct(savedProduct); // Associate image with the product
                        return image;
                    })
                    .collect(Collectors.toList());
            imageRepository.saveAll(images); // Save all images
            savedProduct.setImages(images); // Set images in the product
        }

        return savedProduct;
    }

    @Override
    public Product updateProduct(Long id, @Valid UpdateProductRequest updateProductRequest) {
        // Fetch existing product
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update product fields
        product.setName(updateProductRequest.getName());
        product.setDescription(updateProductRequest.getDescription());
        product.setPrice(updateProductRequest.getPrice());
        product.setStock(updateProductRequest.getStock());
        product.setCategory(updateProductRequest.getCategory()); // Set category directly
        product.setBrand(updateProductRequest.getBrand()); // Set brand directly

        // Update images
        if (updateProductRequest.getImageUrls() != null) {
            // Delete existing images
            imageRepository.deleteByProduct(product);

            // Add new images
            List<Image> images = updateProductRequest.getImageUrls().stream()
                    .map(url -> {
                        Image image = new Image();
                        image.setImageUrl(url);
                        image.setProduct(product); // Associate image with the product
                        return image;
                    })
                    .collect(Collectors.toList());
            imageRepository.saveAll(images); // Save all images
            product.setImages(images); // Set images in the product
        }

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
    public List<Product> getProductsByCategory(Category category) {
        // Fetch products by category
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByBrand(Brand brand) {
        // Fetch products by brand
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        // Fetch products within the specified price range
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        // Fetch products by name (case-insensitive search)
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> getAvailableProducts() {
        // Fetch products with stock greater than 0
        return productRepository.findByStockGreaterThan(0);
    }

    @Override
    public void deleteProduct(Long id) {
        // Delete associated images first
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        imageRepository.deleteByProduct(product);

        // Delete the product
        productRepository.deleteById(id);
    }
}