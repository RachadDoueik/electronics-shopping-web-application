package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.IWishlistItemService;
import mytechshop.mytechshop.models.Wishlist;
import mytechshop.mytechshop.models.WishlistItem;
import mytechshop.mytechshop.models.Product;
import mytechshop.mytechshop.repositories.WishlistItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistItemService implements IWishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;

    public WishlistItemService(WishlistItemRepository wishlistItemRepository){
         this.wishlistItemRepository = wishlistItemRepository;
    }

    @Override
    public WishlistItem addWishlistItem(Wishlist wishlist, Product product) {
        // Check if the product already exists in the wishlist
        Optional<WishlistItem> existingWishlistItem = wishlistItemRepository.findByWishlistAndProduct(wishlist, product);
        if (existingWishlistItem.isPresent()) {
            // If the product already exists, return the existing wishlist item
            return existingWishlistItem.get();
        } else {
            // If the product does not exist, create a new wishlist item
            WishlistItem wishlistItem = new WishlistItem();
            wishlistItem.setWishlist(wishlist);
            wishlistItem.setProduct(product);
            return wishlistItemRepository.save(wishlistItem);
        }
    }

    @Override
    public Optional<WishlistItem> getWishlistItemById(Long id) {
        return wishlistItemRepository.findById(id);
    }

    @Override
    public List<WishlistItem> getWishlistItemsByWishlist(Wishlist wishlist) {
        return wishlistItemRepository.findByWishlist(wishlist);
    }

    @Override
    public void removeWishlistItem(Long id) {
        wishlistItemRepository.deleteById(id);
    }

    @Override
    public void clearWishlistItemsByWishlist(Wishlist wishlist) {
        wishlistItemRepository.deleteByWishlist(wishlist);
    }
}