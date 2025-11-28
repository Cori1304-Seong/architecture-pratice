package cori1304.hexagonal.reservation.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

interface SpringDataReservationRepository extends JpaRepository<ReservationJpaEntity, Long> {
    List<ReservationJpaEntity> findByUserId(Long userId);

    @Query("SELECT r FROM ReservationJpaEntity r WHERE r.lodgingId = :lodgingId " +
           "AND r.checkOutDate >= :checkIn AND r.checkInDate <= :checkOut")
    List<ReservationJpaEntity> findOverlappingReservations(
        @Param("lodgingId") Long lodgingId,
        @Param("checkIn") LocalDate checkIn,
        @Param("checkOut") LocalDate checkOut
    );
}
