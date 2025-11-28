package cori1304.hexagonal.reservation.adapter.out.persistence;

import cori1304.hexagonal.reservation.domain.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@NoArgsConstructor
class ReservationJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lodgingId;
    private Long userId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
    
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public ReservationJpaEntity(Long lodgingId, Long userId, LocalDate checkInDate, 
                              LocalDate checkOutDate, int numberOfGuests, ReservationStatus status) {
        this.lodgingId = lodgingId;
        this.userId = userId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.status = status;
    }
}
