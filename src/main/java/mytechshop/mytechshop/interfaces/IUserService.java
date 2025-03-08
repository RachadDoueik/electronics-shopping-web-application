package mytechshop.mytechshop.interfaces;

import mytechshop.mytechshop.enums.Role;
import mytechshop.mytechshop.models.User;
import java.util.List;
import java.util.Optional;
import mytechshop.mytechshop.requests.*;

public interface IUserService {
    User createUser(CreateUserRequest request);
    User updateUser(Long id, UpdateUserRequest request);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getUsersByRole(Role role);
    boolean existsByEmail(String email);
    void deleteUser(Long id);
}