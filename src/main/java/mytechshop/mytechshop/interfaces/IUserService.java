package mytechshop.mytechshop.interfaces;

import mytechshop.mytechshop.models.User;
import java.util.List;

public interface IUserService {
    User registerUser(User user);
    User getUserById(Long userId);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(User updatedUser);
    void deleteUser(Long userId);
}