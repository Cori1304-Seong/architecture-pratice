package cori1304.hexagonal.lodging.domain.port.out;

import cori1304.hexagonal.lodging.domain.model.Lodging;

public interface SaveLodgingPort {
    Lodging saveLodging(Lodging lodging);
}
