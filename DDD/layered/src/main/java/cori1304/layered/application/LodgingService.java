package cori1304.layered.application;

import cori1304.layered.domain.Lodging;
import cori1304.layered.infrastructure.LodgingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LodgingService {
    private final LodgingRepository lodgingRepository;

    public List<Lodging> getAllLodgings() {
        return lodgingRepository.findAll();
    }

    public Lodging getLodgingById(Long id) {
        return lodgingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lodging not found"));
    }

    @Transactional
    public Lodging createLodging(Lodging lodging) {
        return lodgingRepository.save(lodging);
    }

    @Transactional
    public void updateLodgingAvailability(Long id, boolean isAvailable) {
        Lodging lodging = getLodgingById(id);
        // Update availability logic would go here
    }
}
