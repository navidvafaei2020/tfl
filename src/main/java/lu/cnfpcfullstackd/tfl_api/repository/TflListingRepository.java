package lu.cnfpcfullstackd.tfl_api.repository;

import lu.cnfpcfullstackd.tfl_api.entity.ListingStatus;
import lu.cnfpcfullstackd.tfl_api.entity.TflListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface TflListingRepository extends JpaRepository<TflListing, Long> {
    // JPA provides basic c/rud methods automatically

    // Spring parses method name and generates: SELECT * FROM tfl_listing WHERE status = ?
    List<TflListing> findByStatus(ListingStatus status);

    // SELECT * FROM tfl_listing WHERE business_id = ?
    List<TflListing> findByBusinessId(Long businessId);

    // SELECT * FROM tfl_listing WHERE business_id = ? AND status = ?
    List<TflListing> findByBusinessIdAndStatus(Long businessId, ListingStatus status);

    // SELECT * FROM tfl_listing WHERE expiry_date < ?
    List<TflListing> findByExpiryDateBefore(LocalDateTime dateTime);

    // SELECT * FROM tfl_listing WHERE status = ? AND expiry_date > ?
    List<TflListing> findByStatusAndExpiryDateAfter(ListingStatus status, LocalDateTime dateTime);

    // SELECT COUNT(*) > 0 FROM tfl_listing WHERE title = ?
    boolean existsByTitle(String title);

}
