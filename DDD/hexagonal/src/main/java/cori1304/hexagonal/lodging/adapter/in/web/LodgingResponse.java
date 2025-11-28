package cori1304.hexagonal.lodging.adapter.in.web;

import cori1304.hexagonal.lodging.domain.Lodging;
import lombok.Getter;


@Getter
class LodgingResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final int maxGuests;
    private final boolean isAvailable;
    
    LodgingResponse(Lodging lodging) {
        this.id = lodging.getId();
        this.name = lodging.getName();
        this.description = lodging.getDescription();
        this.maxGuests = lodging.getMaxGuests();
        this.isAvailable = lodging.isAvailable();
    }
}
