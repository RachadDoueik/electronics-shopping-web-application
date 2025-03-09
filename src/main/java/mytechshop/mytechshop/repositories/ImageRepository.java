package mytechshop.mytechshop.repositories;

import mytechshop.mytechshop.models.Image;
import mytechshop.mytechshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProduct(Product product); // Find images by product
    void deleteByProduct(Product product); // Delete images by product
}