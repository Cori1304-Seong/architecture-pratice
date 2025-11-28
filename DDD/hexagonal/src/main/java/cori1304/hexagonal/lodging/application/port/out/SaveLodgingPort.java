package cori1304.hexagonal.lodging.application.port.out;

import cori1304.hexagonal.lodging.domain.Lodging;

public interface SaveLodgingPort {
    Lodging save(Lodging lodging);
}
