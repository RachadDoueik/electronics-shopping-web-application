package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.ICartService;
import mytechshop.mytechshop.models.Cart;
import mytechshop.mytechshop.models.User;
import mytechshop.mytechshop.repositories.CartRepository;
import mytechshop.mytechshop.enums.CartStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService {


    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
         this.cartRepository = cartRepository;
    }

    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setStatus(CartStatus.ACTIVE); // Default status
        cart.setActiveCart(true); // Default active cart
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartByUser(User user) {
        return cartRepository.findActiveCartByUser(user);
    }

    @Override
    public void clearCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getItems().clear(); // Clear all items in the cart
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found with id: " + cartId);
        }
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}