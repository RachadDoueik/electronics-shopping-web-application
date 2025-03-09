package mytechshop.mytechshop.interfaces;

import java.util.List;
import java.util.Optional;
import mytechshop.mytechshop.models.*;
public interface ICartItemService {
    CartItem addCartItem(Cart cart, Product product, int quantity);
    Optional<CartItem> getCartItemById(Long id);
    List<CartItem> getCartItemsByCart(Cart cart);
    void updateCartItemQuantity(Long id, int quantity);
    void removeCartItem(Long id);
    void clearCartItemsByCart(Cart cart);
}
