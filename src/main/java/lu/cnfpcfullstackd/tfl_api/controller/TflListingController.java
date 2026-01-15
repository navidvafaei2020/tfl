package lu.cnfpcfullstackd.tfl_api.controller;

import jakarta.validation.Valid;
import lu.cnfpcfullstackd.tfl_api.dto.request.CreateListingRequestDTO;
import lu.cnfpcfullstackd.tfl_api.dto.request.UpdateListingRequestDTO;
import lu.cnfpcfullstackd.tfl_api.dto.response.ListingResponseDTO;
import lu.cnfpcfullstackd.tfl_api.entity.TflListing;
import lu.cnfpcfullstackd.tfl_api.service.TflListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/listings")
public class TflListingController {

    @Autowired
    private TflListingService listingService;


    // Get All listings
    //anyone can view lisings
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<ListingResponseDTO> getAllListings(){
        return listingService.getAllListings();
    }


    // Get listing by ID
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ListingResponseDTO getListingById(@PathVariable Long id){
        return listingService.getListingById(id);
    }


    // POST - Create new listing
    @PostMapping
    @PreAuthorize("hasAnyRole('BUSINESS','ADMIN')")
    public ResponseEntity<ListingResponseDTO> createListing(
            @Valid @RequestBody CreateListingRequestDTO dto){
        ListingResponseDTO created = listingService.createListing(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping("/search")
    public List<ListingResponseDTO> getListingBystatus(@RequestParam String status){
        return listingService.searchByStatus(status);
    }


    // PUT - Update listing
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('BUSINESS')")
    public ListingResponseDTO updateListing(
            @PathVariable Long id,
            @Valid @RequestBody UpdateListingRequestDTO updateListing) {
        return listingService.updateListing(id, updateListing);
    }


    // DELETE - Remove listing
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('BUSINESS')")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }


}
