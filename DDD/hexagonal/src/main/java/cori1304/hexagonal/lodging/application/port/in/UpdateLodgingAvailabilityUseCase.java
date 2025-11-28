package cori1304.hexagonal.lodging.application.port.in;

import cori1304.hexagonal.lodging.domain.Lodging;

public interface UpdateLodgingAvailabilityUseCase {
    Lodging updateLodgingAvailability(Long id, boolean isAvailable);
}
