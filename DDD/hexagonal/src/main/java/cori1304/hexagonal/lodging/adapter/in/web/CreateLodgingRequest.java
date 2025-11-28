package cori1304.hexagonal.lodging.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
class CreateLodgingRequest {
    private String name;
    private String description;
    private int maxGuests;
    private boolean isAvailable;
}
