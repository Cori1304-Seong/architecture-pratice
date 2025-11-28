package cori1304.hexagonal.reservation.adapter.out.persistence;

import cori1304.hexagonal.reservation.application.port.out.LoadReservationPort;
import cori1304.hexagonal.reservation.application.port.out.SaveReservationPort;
import cori1304.hexagonal.reservation.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class ReservationPersistenceAdapter implements LoadReservationPort, SaveReservationPort {
    private final SpringDataReservationRepository repository;
    private final ReservationMapper mapper;

    @Override
    public Optional<Reservation> findById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToDomainEntity);
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findOverlappingReservations(Long lodgingId, LocalDate checkIn, LocalDate checkOut) {
        return repository.findOverlappingReservations(lodgingId, checkIn, checkOut).stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationJpaEntity saved = repository.save(mapper.mapToJpaEntity(reservation));
        return mapper.mapToDomainEntity(saved);
    }
}
