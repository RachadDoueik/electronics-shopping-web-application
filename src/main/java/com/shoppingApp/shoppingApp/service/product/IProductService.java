package com.shoppingApp.shoppingApp.service.product;
import com.shoppingApp.shoppingApp.model.Product;
import com.shoppingApp.shoppingApp.requests.AddProductRequest;
import com.shoppingApp.shoppingApp.requests.UpdateProductRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest addProductRequest);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(UpdateProductRequest updateProductRequest, Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String categoryName);

    List<Product> getProductByBrand(String brandName);

    List<Product> getProductByCategoryAndBrand(String categoryName, String brandName);

    List<Product> getProductByName(String productName);

    List<Product> getProductByBrandAndName(String brandName, String productName);

    Long getProductCountByBrandAndName(String brandName, String productName);

}
