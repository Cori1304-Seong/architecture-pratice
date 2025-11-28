package cori1304.hexagonal.lodging.application.port.in;

import cori1304.hexagonal.lodging.domain.Lodging;
import java.util.List;

public interface GetLodgingUseCase {
    List<Lodging> getAllLodgings();
    Lodging getLodgingById(Long id);
}
