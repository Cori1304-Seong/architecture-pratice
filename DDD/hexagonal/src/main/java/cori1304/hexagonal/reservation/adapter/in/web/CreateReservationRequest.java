package cori1304.hexagonal.reservation.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
class CreateReservationRequest {
    private Long lodgingId;
    private Long userId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
}
