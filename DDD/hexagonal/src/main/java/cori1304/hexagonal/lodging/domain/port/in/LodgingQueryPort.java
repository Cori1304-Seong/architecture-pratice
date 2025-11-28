package cori1304.hexagonal.lodging.domain.port.in;

import cori1304.hexagonal.lodging.domain.model.Lodging;
import java.util.List;

public interface LodgingQueryPort {
    Lodging getLodgingById(Long id);
    List<Lodging> getAllLodgings();
}
