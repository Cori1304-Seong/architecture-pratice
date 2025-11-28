package cori1304.hexagonal.reservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class Reservation {
    private final Long id;
    private final Long lodgingId;
    private final Long userId;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int numberOfGuests;
    private final ReservationStatus status;

    public Reservation confirm() {
        if (status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Can only confirm pending reservations");
        }
        return new Reservation(id, lodgingId, userId, checkInDate, checkOutDate, numberOfGuests, ReservationStatus.CONFIRMED);
    }

    public Reservation cancel() {
        if (status == ReservationStatus.CANCELLED) {
            throw new IllegalStateException("Reservation is already cancelled");
        }
        return new Reservation(id, lodgingId, userId, checkInDate, checkOutDate, numberOfGuests, ReservationStatus.CANCELLED);
    }

    public boolean overlaps(LocalDate otherCheckIn, LocalDate otherCheckOut) {
        return !checkOutDate.isBefore(otherCheckIn) && !checkInDate.isAfter(otherCheckOut);
    }
}
