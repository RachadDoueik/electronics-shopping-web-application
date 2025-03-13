package mytechshop.mytechshop.services;

import mytechshop.mytechshop.requests.CreateUserRequest;
import mytechshop.mytechshop.requests.LoginRequest;
import mytechshop.mytechshop.models.User;
import mytechshop.mytechshop.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CartService cartService; // Add CartService
    private final WishlistService wishlistService; // Add WishlistService

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            CartService cartService, // Inject CartService
            WishlistService wishlistService // Inject WishlistService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
        this.wishlistService = wishlistService;
    }

    public User signUp(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt password
        user.setRole(request.getRole());

        // Save the user
        User savedUser = userRepository.save(user);

        // Create a cart for the user
        cartService.createCart(savedUser);

        // Create a wishlist for the user
        wishlistService.createWishlist(savedUser);

        return savedUser;
    }

    public User login(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}