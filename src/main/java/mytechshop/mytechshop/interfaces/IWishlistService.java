package mytechshop.mytechshop.interfaces;

import java.util.Optional;
import mytechshop.mytechshop.models.*;

public interface IWishlistService {
    Wishlist createWishlist(User user);
    Optional<Wishlist> getWishlistByUser(User user);
    void deleteWishlist(Long id);
}
