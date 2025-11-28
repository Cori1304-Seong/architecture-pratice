package cori1304.hexagonal.lodging.domain.port.in;

import cori1304.hexagonal.lodging.domain.model.Lodging;
import java.math.BigDecimal;

public interface LodgingCommandPort {
    Lodging createLodging(String name, String address, String description, 
                         BigDecimal pricePerNight, Integer maxGuests);
    void updateLodgingAvailability(Long id, boolean isAvailable);
}
