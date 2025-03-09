package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.ICartItemService;
import mytechshop.mytechshop.models.Cart;
import mytechshop.mytechshop.models.CartItem;
import mytechshop.mytechshop.models.Product;
import mytechshop.mytechshop.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService implements ICartItemService {


    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository){
         this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem addCartItem(Cart cart, Product product, int quantity) {
        // Check if the product already exists in the cart
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
        if (existingCartItem.isPresent()) {
            // If the product already exists, update the quantity
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartItemRepository.save(cartItem);
        } else {
            // If the product does not exist, create a new cart item
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            return cartItemRepository.save(cartItem);
        }
    }

    @Override
    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public List<CartItem> getCartItemsByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    @Override
    public void updateCartItemQuantity(Long id, int quantity) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found with id: " + id));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void clearCartItemsByCart(Cart cart) {
        cartItemRepository.deleteByCart(cart);
    }
}