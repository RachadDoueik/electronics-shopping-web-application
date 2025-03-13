package mytechshop.mytechshop.controllers;
import mytechshop.mytechshop.models.Product;
import mytechshop.mytechshop.requests.CreateProductRequest;
import mytechshop.mytechshop.requests.UpdateProductRequest;
import mytechshop.mytechshop.responses.CreateProductResponse;
import mytechshop.mytechshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
          this.productService = productService;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@ModelAttribute CreateProductRequest createProductRequest) {
        Product product = productService.createProduct(createProductRequest);
        CreateProductResponse res = new CreateProductResponse(product);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @ModelAttribute UpdateProductRequest updateProductRequest) {
        Product product = productService.updateProduct(id, updateProductRequest);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get products by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable long categoryId) {
        // Assuming you have a method in ProductService to fetch products by category ID
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get products by brand
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable Long brandId) {
        // Assuming you have a method in ProductService to fetch products by brand ID
        List<Product> products = productService.getProductsByBrandId(brandId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get products by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Search products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.searchProductsByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get available products (stock > 0)
    @GetMapping("/available")
    public ResponseEntity<List<Product>> getAvailableProducts() {
        List<Product> products = productService.getAvailableProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}