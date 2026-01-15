package lu.cnfpcfullstackd.tfl_api.repository;

import lu.cnfpcfullstackd.tfl_api.entity.TflListing;
import lu.cnfpcfullstackd.tfl_api.entity.TflUser;
import lu.cnfpcfullstackd.tfl_api.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TflUserRepository extends JpaRepository<TflUser, Long> {
    // Find user by username (for login)
    Optional<TflUser> findByUsername(String username);

    // Find user by email
    Optional<TflUser> findByEmail(String email);

    // Check if username exists (for registration)
    boolean existsByUsername(String username);

    // Check if email exists
    boolean existsByEmail(String email);

    List<TflUser> findByRole(UserRole role);

}
