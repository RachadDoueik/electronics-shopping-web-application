package mytechshop.mytechshop.services;

import lombok.RequiredArgsConstructor;
import mytechshop.mytechshop.models.Category;
import mytechshop.mytechshop.models.Product;
import mytechshop.mytechshop.repositories.ProductRepository;
import mytechshop.mytechshop.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return List.of();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}