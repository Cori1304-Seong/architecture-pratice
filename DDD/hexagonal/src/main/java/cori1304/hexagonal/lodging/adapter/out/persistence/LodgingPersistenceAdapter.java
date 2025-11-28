package cori1304.hexagonal.lodging.adapter.out.persistence;

import cori1304.hexagonal.lodging.domain.Lodging;
import cori1304.hexagonal.lodging.application.port.out.LoadLodgingPort;
import cori1304.hexagonal.lodging.application.port.out.SaveLodgingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class LodgingPersistenceAdapter implements LoadLodgingPort, SaveLodgingPort {
    
    private final SpringDataLodgingRepository lodgingRepository;
    private final LodgingMapper lodgingMapper;
    
    @Override
    public List<Lodging> findAll() {
        return lodgingRepository.findAll().stream()
            .map(lodgingMapper::mapToDomainEntity)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Lodging> findById(Long id) {
        return lodgingRepository.findById(id)
            .map(lodgingMapper::mapToDomainEntity);
    }
    
    @Override
    public Lodging save(Lodging lodging) {
        LodgingJpaEntity savedLodging = lodgingRepository.save(
            lodgingMapper.mapToJpaEntity(lodging)
        );
        return lodgingMapper.mapToDomainEntity(savedLodging);
    }
}
