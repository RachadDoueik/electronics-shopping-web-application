package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.IWishlistService;
import mytechshop.mytechshop.models.Wishlist;
import mytechshop.mytechshop.models.User;
import mytechshop.mytechshop.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService implements IWishlistService {


    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository){
         this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist createWishlist(User user) {
        // Check if the user already has a wishlist
        Optional<Wishlist> existingWishlist = wishlistRepository.findByUser(user);
        if (existingWishlist.isPresent()) {
            throw new RuntimeException("User already has a wishlist");
        }

        // Create a new wishlist
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Optional<Wishlist> getWishlistByUser(User user) {
        return wishlistRepository.findByUser(user);
    }

    @Override
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}