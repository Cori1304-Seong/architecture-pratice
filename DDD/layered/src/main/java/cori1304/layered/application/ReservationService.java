package cori1304.layered.application;

import cori1304.layered.domain.Lodging;
import cori1304.layered.domain.Reservation;
import cori1304.layered.domain.User;
import cori1304.layered.infrastructure.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final LodgingService lodgingService;
    private final UserService userService;

    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Transactional
    public Reservation createReservation(Long lodgingId, Long userId, LocalDate checkInDate, LocalDate checkOutDate, Integer numberOfGuests) {
        Lodging lodging = lodgingService.getLodgingById(lodgingId);
        User user = userService.getUserById(userId);

        if (numberOfGuests > lodging.getMaxGuests()) {
            throw new RuntimeException("Number of guests exceeds lodging capacity");
        }

        // Check if the lodging is available for the given dates
        List<Reservation> existingReservations = reservationRepository
                .findByLodgingIdAndCheckInDateGreaterThanEqual(lodgingId, checkInDate);
        
        // Validation logic for overlapping dates would go here

        Reservation reservation = new Reservation(lodging, user, checkInDate, checkOutDate, numberOfGuests);
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void confirmReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.confirm();
        reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.cancel();
        reservationRepository.save(reservation);
    }
}
