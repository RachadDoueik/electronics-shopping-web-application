package mytechshop.mytechshop.controllers;

import mytechshop.mytechshop.models.Cart;
import mytechshop.mytechshop.models.User;
import mytechshop.mytechshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // Create a new cart for a user
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody User user) {
        Cart cart = cartService.createCart(user);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    // Get the active cart for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUser(@PathVariable Long userId) {
        // Create a dummy user object with the provided userId
        User user = new User();
        user.setId(userId);

        Optional<Cart> cartOptional = cartService.getCartByUser(user);
        return cartOptional.map(cart -> new ResponseEntity<>(cart, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Clear all items in a cart
    @PutMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete a cart
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}