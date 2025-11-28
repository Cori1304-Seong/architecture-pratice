package cori1304.hexagonal.lodging.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Lodging {
    private final Long id;
    private final String name;
    private final String description;
    private final int maxGuests;
    private final boolean isAvailable;

    public Lodging withAvailability(boolean isAvailable) {
        return new Lodging(
            this.id,
            this.name,
            this.description,
            this.maxGuests,
            isAvailable
        );
    }
}
