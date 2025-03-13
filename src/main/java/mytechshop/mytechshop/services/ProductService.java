package mytechshop.mytechshop.services;

import mytechshop.mytechshop.models.Color;
import mytechshop.mytechshop.models.Image;
import mytechshop.mytechshop.models.Product;
import mytechshop.mytechshop.repositories.ProductRepository;
import mytechshop.mytechshop.requests.CreateProductRequest;
import mytechshop.mytechshop.requests.UpdateProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageService imageService;

    public ProductService(ProductRepository productRepository, ImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
    }

    @Transactional
    public Product createProduct(CreateProductRequest createProductRequest) {
        // Create product entity
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        product.setStock(createProductRequest.getStock());
        product.setCategory(createProductRequest.getCategory());
        product.setBrand(createProductRequest.getBrand());
        product.setColor(createProductRequest.getColor());

        // Save product first
        Product savedProduct = productRepository.save(product);

        // Upload images and associate them with the product
        if (createProductRequest.getImages() != null && !createProductRequest.getImages().isEmpty()) {
            List<String> imageUrls = imageService.uploadImages(createProductRequest.getImages());
            List<Image> images = imageService.saveImages(imageUrls, savedProduct);
            savedProduct.setImages(images);
        }

        return savedProduct;
    }

    @Transactional
    public Product updateProduct(Long id, UpdateProductRequest updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Update basic fields
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setColor(updatedProduct.getColor());

        // Update image URLs
        if (updatedProduct.getImageUrls() != null) {
            List<Image> images = imageService.saveImages(updatedProduct.getImageUrls(), existingProduct);
            existingProduct.setImages(images);
        }

        return productRepository.save(existingProduct);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getProductsByBrandId(Long brandId) {
        return productRepository.findByBrandId(brandId);
    }

    public List<Product> getProductsByColor(Color color) {
        return productRepository.findByColor(color);
    }

    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findByStockGreaterThan(0);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Delete associated images
        imageService.deleteImagesByProduct(product);

        // Delete the product
        productRepository.deleteById(id);
    }
}