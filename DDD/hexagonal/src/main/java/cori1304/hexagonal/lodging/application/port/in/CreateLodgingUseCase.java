package cori1304.hexagonal.lodging.application.port.in;

import cori1304.hexagonal.lodging.domain.Lodging;

public interface CreateLodgingUseCase {
    Lodging createLodging(Lodging lodging);
}
