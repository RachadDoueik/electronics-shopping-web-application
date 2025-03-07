package mytechshop.mytechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByWishlist(Wishlist wishlist);
    Optional<WishlistItem> findByWishlistAndProduct(Wishlist wishlist, Product product);
    void deleteByWishlist(Wishlist wishlist);
}