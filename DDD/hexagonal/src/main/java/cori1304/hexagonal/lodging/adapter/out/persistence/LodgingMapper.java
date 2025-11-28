package cori1304.hexagonal.lodging.adapter.out.persistence;

import cori1304.hexagonal.lodging.domain.Lodging;
import org.springframework.stereotype.Component;

@Component
class LodgingMapper {
    
    Lodging mapToDomainEntity(LodgingJpaEntity lodgingJpaEntity) {
        return new Lodging(
            lodgingJpaEntity.getId(),
            lodgingJpaEntity.getName(),
            lodgingJpaEntity.getDescription(),
            lodgingJpaEntity.getMaxGuests(),
            lodgingJpaEntity.isAvailable()
        );
    }
    
    LodgingJpaEntity mapToJpaEntity(Lodging lodging) {
        return new LodgingJpaEntity(
            lodging.getName(),
            lodging.getDescription(),
            lodging.getMaxGuests(),
            lodging.isAvailable()
        );
    }
}
