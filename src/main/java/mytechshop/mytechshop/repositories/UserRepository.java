package mytechshop.mytechshop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import mytechshop.mytechshop.models.*;
import mytechshop.mytechshop.enums.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    Boolean existsByEmail(String email);
    void deleteByEmail(String email);
}