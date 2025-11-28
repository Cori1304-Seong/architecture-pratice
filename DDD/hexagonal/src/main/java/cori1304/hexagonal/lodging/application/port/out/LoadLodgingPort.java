package cori1304.hexagonal.lodging.application.port.out;

import cori1304.hexagonal.lodging.domain.Lodging;
import java.util.List;
import java.util.Optional;

public interface LoadLodgingPort {
    List<Lodging> findAll();
    Optional<Lodging> findById(Long id);
}
