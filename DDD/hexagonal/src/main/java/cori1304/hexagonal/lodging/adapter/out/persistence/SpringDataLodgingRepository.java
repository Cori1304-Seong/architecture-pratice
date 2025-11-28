package cori1304.hexagonal.lodging.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataLodgingRepository extends JpaRepository<LodgingJpaEntity, Long> {
}
