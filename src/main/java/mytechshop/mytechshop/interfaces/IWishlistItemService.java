package mytechshop.mytechshop.interfaces;

import java.util.List;
import java.util.Optional;
import mytechshop.mytechshop.models.*;

public interface IWishlistItemService {
    WishlistItem addWishlistItem(Wishlist wishlist, Product product);
    Optional<WishlistItem> getWishlistItemById(Long id);
    List<WishlistItem> getWishlistItemsByWishlist(Wishlist wishlist);
    void removeWishlistItem(Long id);
    void clearWishlistItemsByWishlist(Wishlist wishlist);
}