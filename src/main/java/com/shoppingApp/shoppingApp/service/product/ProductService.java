package com.shoppingApp.shoppingApp.service.product;
import com.shoppingApp.shoppingApp.repository.ProductRepository;
import com.shoppingApp.shoppingApp.repository.CategoryRepository;
import com.shoppingApp.shoppingApp.requests.AddProductRequest;
import com.shoppingApp.shoppingApp.requests.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import com.shoppingApp.shoppingApp.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.shoppingApp.shoppingApp.exceptions.*;

@Service
@RequiredArgsConstructor

public class ProductService implements IProductService {

    //Injected dependency is always declared as final
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
        //1 - check if the category exists already
        Category category = Optional.ofNullable(categoryRepository.findByName(addProductRequest.getCategory().getName()))
                .orElseGet(
                        () -> {
                             //category does not exist -> create a new one and save it to the database
                             Category newCategory = new Category(addProductRequest.getCategory().getName());
                             return categoryRepository.save(newCategory);
                        }
                );
        //after checking for the category , set the request's category to 'category'
        addProductRequest.setCategory(category);
        //save the new product to the database
        return productRepository.save(createProduct(addProductRequest , category));

    }

    //method to create a product that can be added using a request(without interacting directly with the model)
    private Product createProduct(AddProductRequest request , Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getDescription(),
                request.getPrice(),
                request.getInventory(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found !"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete , ()-> {throw new ProductNotFoundException("Product Not Found !");});
    }

    @Override
    public Product updateProduct(UpdateProductRequest updateProductRequest, Long productId) {
           return productRepository.findById(productId)
                   .map(existingProduct -> updateExistingProduct(existingProduct , updateProductRequest))
                   .map(productRepository::save)
                   .orElseThrow(()-> new ProductNotFoundException("Product Not Found !"));
    }

    //method to update a product and return it

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest updateProductRequest) {
           existingProduct.setName(updateProductRequest.getName());
           existingProduct.setBrand(updateProductRequest.getBrand());
           existingProduct.setDescription(updateProductRequest.getDescription());
           existingProduct.setPrice(updateProductRequest.getPrice());
           existingProduct.setInventory(updateProductRequest.getInventory());
           Category category = categoryRepository.findByName(updateProductRequest.getCategory().getName());
           existingProduct.setCategory(category);
           return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Product> getProductByBrand(String brandName) {
        return productRepository.findByBrand(brandName);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String categoryName, String brandName) {
        return productRepository.findByCategoryNameAndBrand(categoryName , brandName);
    }

    @Override
    public List<Product> getProductByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brandName, String productName) {
        return productRepository.findByBrandAndName(brandName , productName);
    }

    @Override
    public Long getProductCountByBrandAndName(String brandName, String productName) {
        return productRepository.countProductByBrandAndName(brandName , productName);
    }
}
