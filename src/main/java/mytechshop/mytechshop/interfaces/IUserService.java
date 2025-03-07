package mytechshop.mytechshop.interfaces;

import mytechshop.mytechshop.enums.Role;
import mytechshop.mytechshop.models.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User saveUser(User user);
    User updateUser(Long id, User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getUsersByRole(Role role);
    boolean existsByEmail(String email);
    void deleteUser(Long id);
}
