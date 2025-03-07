package mytechshop.mytechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Find the cart by user
    Optional<Cart> findActiveCartByUser(User user);

    // Find the cart by cartId
    Optional<Cart> findCartById(Long cartId);

    // Find all carts by user
    List<Cart> findCartsByUser(User user);
}