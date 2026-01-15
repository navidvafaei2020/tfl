package lu.cnfpcfullstackd.tfl_api.service;

import lu.cnfpcfullstackd.tfl_api.dto.request.CreateListingRequestDTO;
import lu.cnfpcfullstackd.tfl_api.dto.request.UpdateListingRequestDTO;
import lu.cnfpcfullstackd.tfl_api.dto.response.ListingResponseDTO;
import lu.cnfpcfullstackd.tfl_api.entity.ListingStatus;
import lu.cnfpcfullstackd.tfl_api.entity.TflListing;
import lu.cnfpcfullstackd.tfl_api.entity.TflUser;
import lu.cnfpcfullstackd.tfl_api.entity.UserRole;
import lu.cnfpcfullstackd.tfl_api.exception.DuplicateResourceException;
import lu.cnfpcfullstackd.tfl_api.exception.ResourceNotFoundException;
import lu.cnfpcfullstackd.tfl_api.mapper.ListingMapper;
import lu.cnfpcfullstackd.tfl_api.repository.TflListingRepository;
import lu.cnfpcfullstackd.tfl_api.repository.TflUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TflListingService {

    @Autowired
    private TflListingRepository repository;

    @Autowired
    private  TflUserRepository userRepository;

    // Get all listings
    public List<ListingResponseDTO> getAllListings() {
        return repository.findAll()
                .stream()
                .map(ListingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    // Get listing by ID
    public ListingResponseDTO getListingById(Long id) {
        TflListing listing = repository.getReferenceById(id);
         listing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing", id));

        return ListingMapper.toResponseDTO(listing);
    }


    // Create new listing
    public ListingResponseDTO createListing(CreateListingRequestDTO dto) {
        //Find corresponding business TflUser
        TflUser business = userRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("TflUser (Business)", dto.getBusinessId()));
        // Verify it's actually a business
        if  (business.getRole() != UserRole.BUSINESS){
            throw new RuntimeException("User with id " + dto.getBusinessId() + " is not a business");
        }

        // Convert DTO to entity
        TflListing listing = ListingMapper.toEntity(dto);

        // Set relationship
        listing.setBusiness(business);

        // Business logic: Always set status to AVAILABLE when creating
        if (repository.existsByTitle(listing.getTitle())) {
            throw new DuplicateResourceException("Listing", "title", listing.getTitle());
        }
        listing.setStatus(ListingStatus.AVAILABLE);

        TflListing savedListing = repository.save(listing);
        return ListingMapper.toResponseDTO(savedListing);
    }


    // Search by status
    public List<ListingResponseDTO> searchByStatus(String status) {
        if (status != null) {
            ListingStatus listingStatus = ListingStatus.valueOf(status.toUpperCase());
        return repository.findByStatus(listingStatus)
                .stream()
                .map(ListingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

        return repository.findAll()
             .stream()
                .map(ListingMapper::toResponseDTO)
                .collect(Collectors.toList());

    }


    // Update listing
    // PUT - receives RequestDTO, returns ResponseDTO
    public ListingResponseDTO updateListing(Long id, UpdateListingRequestDTO dto) {
        TflListing listing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing", id));

        // Update entity from DTO
        ListingMapper.updateEntity(listing, dto);

        // Save and return
        TflListing updated = repository.save(listing);
        return ListingMapper.toResponseDTO(updated);
    }


    // Delete listing
    public void deleteListing(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Listing not found with id: " + id);
        }
        repository.deleteById(id);
    }

}
