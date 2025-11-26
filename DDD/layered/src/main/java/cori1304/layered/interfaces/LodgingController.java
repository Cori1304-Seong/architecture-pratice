package cori1304.layered.interfaces;

import cori1304.layered.domain.Lodging;
import cori1304.layered.application.LodgingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lodgings")
@RequiredArgsConstructor
public class LodgingController {
    private final LodgingService lodgingService;

    @GetMapping
    public ResponseEntity<List<Lodging>> getAllLodgings() {
        return ResponseEntity.ok(lodgingService.getAllLodgings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lodging> getLodgingById(@PathVariable Long id) {
        return ResponseEntity.ok(lodgingService.getLodgingById(id));
    }

    @PostMapping
    public ResponseEntity<Lodging> createLodging(@RequestBody Lodging lodging) {
        return ResponseEntity.ok(lodgingService.createLodging(lodging));
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Void> updateLodgingAvailability(@PathVariable Long id, @RequestParam boolean isAvailable) {
        lodgingService.updateLodgingAvailability(id, isAvailable);
        return ResponseEntity.ok().build();
    }
}
