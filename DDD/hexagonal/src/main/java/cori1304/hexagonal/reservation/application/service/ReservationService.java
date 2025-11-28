package cori1304.hexagonal.reservation.application.service;

import cori1304.hexagonal.reservation.application.port.in.CreateReservationCommand;
import cori1304.hexagonal.reservation.application.port.in.CreateReservationUseCase;
import cori1304.hexagonal.reservation.application.port.in.GetReservationUseCase;
import cori1304.hexagonal.reservation.application.port.in.ManageReservationUseCase;
import cori1304.hexagonal.reservation.application.port.out.LoadReservationPort;
import cori1304.hexagonal.reservation.application.port.out.SaveReservationPort;
import cori1304.hexagonal.reservation.domain.Reservation;
import cori1304.hexagonal.reservation.domain.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ReservationService implements CreateReservationUseCase, GetReservationUseCase, ManageReservationUseCase {
    private final LoadReservationPort loadReservationPort;
    private final SaveReservationPort saveReservationPort;

    @Override
    @Transactional
    public Reservation createReservation(CreateReservationCommand command) {
        // Check for overlapping reservations
        List<Reservation> overlappingReservations = loadReservationPort.findOverlappingReservations(
            command.getLodgingId(),
            command.getCheckInDate(),
            command.getCheckOutDate()
        );

        if (!overlappingReservations.isEmpty()) {
            throw new IllegalStateException("The lodging is not available for the selected dates");
        }

        Reservation reservation = new Reservation(
            null,
            command.getLodgingId(),
            command.getUserId(),
            command.getCheckInDate(),
            command.getCheckOutDate(),
            command.getNumberOfGuests(),
            ReservationStatus.PENDING
        );

        return saveReservationPort.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long userId) {
        return loadReservationPort.findByUserId(userId);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return loadReservationPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    @Transactional
    public Reservation confirmReservation(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);
        return saveReservationPort.save(reservation.confirm());
    }

    @Override
    @Transactional
    public Reservation cancelReservation(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);
        return saveReservationPort.save(reservation.cancel());
    }
}
