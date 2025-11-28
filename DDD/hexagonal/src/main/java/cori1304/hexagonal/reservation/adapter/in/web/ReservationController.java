package cori1304.hexagonal.reservation.adapter.in.web;

import cori1304.hexagonal.reservation.application.port.in.CreateReservationCommand;
import cori1304.hexagonal.reservation.application.port.in.CreateReservationUseCase;
import cori1304.hexagonal.reservation.application.port.in.GetReservationUseCase;
import cori1304.hexagonal.reservation.application.port.in.ManageReservationUseCase;
import cori1304.hexagonal.reservation.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
class ReservationController {
    private final CreateReservationUseCase createReservationUseCase;
    private final GetReservationUseCase getReservationUseCase;
    private final ManageReservationUseCase manageReservationUseCase;

    @PostMapping
    ResponseEntity<ReservationResponse> createReservation(@RequestBody CreateReservationRequest request) {
        CreateReservationCommand command = new CreateReservationCommand(
            request.getLodgingId(),
            request.getUserId(),
            request.getCheckInDate(),
            request.getCheckOutDate(),
            request.getNumberOfGuests()
        );

        Reservation reservation = createReservationUseCase.createReservation(command);
        return ResponseEntity.ok(new ReservationResponse(reservation));
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<List<ReservationResponse>> getReservationsByUserId(@PathVariable Long userId) {
        List<ReservationResponse> reservations = getReservationUseCase.getReservationsByUserId(userId)
                .stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    ResponseEntity<ReservationResponse> getReservation(@PathVariable Long id) {
        Reservation reservation = getReservationUseCase.getReservationById(id);
        return ResponseEntity.ok(new ReservationResponse(reservation));
    }

    @PostMapping("/{id}/confirm")
    ResponseEntity<ReservationResponse> confirmReservation(@PathVariable Long id) {
        Reservation reservation = manageReservationUseCase.confirmReservation(id);
        return ResponseEntity.ok(new ReservationResponse(reservation));
    }

    @PostMapping("/{id}/cancel")
    ResponseEntity<ReservationResponse> cancelReservation(@PathVariable Long id) {
        Reservation reservation = manageReservationUseCase.cancelReservation(id);
        return ResponseEntity.ok(new ReservationResponse(reservation));
    }
}
