package cori1304.hexagonal.lodging.adapter.in.web;

import cori1304.hexagonal.lodging.domain.Lodging;
import cori1304.hexagonal.lodging.application.port.in.CreateLodgingUseCase;
import cori1304.hexagonal.lodging.application.port.in.GetLodgingUseCase;
import cori1304.hexagonal.lodging.application.port.in.UpdateLodgingAvailabilityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lodgings")
@RequiredArgsConstructor
class LodgingController {
    
    private final CreateLodgingUseCase createLodgingUseCase;
    private final GetLodgingUseCase getLodgingUseCase;
    private final UpdateLodgingAvailabilityUseCase updateLodgingAvailabilityUseCase;
    
    @PostMapping
    ResponseEntity<LodgingResponse> createLodging(@RequestBody CreateLodgingRequest request) {
        Lodging lodging = createLodgingUseCase.createLodging(new Lodging(
            null,
            request.getName(),
            request.getDescription(),
            request.getMaxGuests(),
            request.isAvailable()
        ));
        return ResponseEntity.ok(new LodgingResponse(lodging));
    }

    @GetMapping
    ResponseEntity<List<LodgingResponse>> getAllLodgings() {
        List<LodgingResponse> lodgings = getLodgingUseCase.getAllLodgings().stream()
                .map(LodgingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lodgings);
    }

    @GetMapping("/{id}")
    ResponseEntity<LodgingResponse> getLodging(@PathVariable Long id) {
        Lodging lodging = getLodgingUseCase.getLodgingById(id);
        return ResponseEntity.ok(new LodgingResponse(lodging));
    }

    @PatchMapping("/{id}/availability")
    ResponseEntity<Void> updateAvailability(@PathVariable Long id, @RequestParam boolean available) {
        updateLodgingAvailabilityUseCase.updateLodgingAvailability(id, available);
        return ResponseEntity.ok().build();
    }
}
