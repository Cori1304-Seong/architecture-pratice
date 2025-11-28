package cori1304.hexagonal.reservation.adapter.out.persistence;

import cori1304.hexagonal.reservation.domain.Reservation;
import org.springframework.stereotype.Component;

@Component
class ReservationMapper {
    Reservation mapToDomainEntity(ReservationJpaEntity reservationJpaEntity) {
        return new Reservation(
            reservationJpaEntity.getId(),
            reservationJpaEntity.getLodgingId(),
            reservationJpaEntity.getUserId(),
            reservationJpaEntity.getCheckInDate(),
            reservationJpaEntity.getCheckOutDate(),
            reservationJpaEntity.getNumberOfGuests(),
            reservationJpaEntity.getStatus()
        );
    }

    ReservationJpaEntity mapToJpaEntity(Reservation reservation) {
        return new ReservationJpaEntity(
            reservation.getLodgingId(),
            reservation.getUserId(),
            reservation.getCheckInDate(),
            reservation.getCheckOutDate(),
            reservation.getNumberOfGuests(),
            reservation.getStatus()
        );
    }
}
