package mytechshop.mytechshop.interfaces;

import java.util.Optional;
import mytechshop.mytechshop.models.*;

public interface ICartService {
    Cart createCart(User user);
    Optional<Cart> getCartByUser(User user);
    void clearCart(Long cartId);
    void deleteCart(Long id);
}