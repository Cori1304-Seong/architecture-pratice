package cori1304.hexagonal.lodging.domain.port.out;

import cori1304.hexagonal.lodging.domain.model.Lodging;
import java.util.Optional;
import java.util.List;

public interface LoadLodgingPort {
    Optional<Lodging> loadLodging(Long id);
    List<Lodging> loadAllLodgings();
}
