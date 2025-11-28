package cori1304.hexagonal.reservation.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class CreateReservationCommand {
    private final Long lodgingId;
    private final Long userId;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int numberOfGuests;
}
