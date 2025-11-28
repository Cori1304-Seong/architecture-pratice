package cori1304.hexagonal.lodging.application.service;

import cori1304.hexagonal.lodging.application.port.in.CreateLodgingUseCase;
import cori1304.hexagonal.lodging.application.port.in.GetLodgingUseCase;
import cori1304.hexagonal.lodging.application.port.in.UpdateLodgingAvailabilityUseCase;
import cori1304.hexagonal.lodging.application.port.out.LoadLodgingPort;
import cori1304.hexagonal.lodging.application.port.out.SaveLodgingPort;
import cori1304.hexagonal.lodging.domain.Lodging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class LodgingService implements GetLodgingUseCase, CreateLodgingUseCase, UpdateLodgingAvailabilityUseCase {
    private final LoadLodgingPort loadLodgingPort;
    private final SaveLodgingPort saveLodgingPort;

    @Override
    public List<Lodging> getAllLodgings() {
        return loadLodgingPort.findAll();
    }

    @Override
    public Lodging getLodgingById(Long id) {
        return loadLodgingPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Lodging not found"));
    }

    @Override
    @Transactional
    public Lodging createLodging(Lodging lodging) {
        return saveLodgingPort.save(lodging);
    }

    @Override
    @Transactional
    public Lodging updateLodgingAvailability(Long id, boolean isAvailable) {
        Lodging lodging = getLodgingById(id);
        Lodging updatedLodging = lodging.withAvailability(isAvailable);
        return saveLodgingPort.save(updatedLodging);
    }
}
