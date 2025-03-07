package mytechshop.mytechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Find cart items by cart
    List<CartItem> findByCart(Cart cart);

    // Find cart items by product
    List<CartItem> findByProduct(Product product);
}
