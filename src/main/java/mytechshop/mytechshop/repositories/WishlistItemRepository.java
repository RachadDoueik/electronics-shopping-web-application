package mytechshop.mytechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;
import java.util.List;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    // Custom query methods can be added here
    List<WishlistItem> findByWishlistId(Long wishlistId); // Example: Find wishlist items by wishlist ID
}