package cori1304.layered.infrastructure;

import cori1304.layered.domain.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LodgingRepository extends JpaRepository<Lodging, Long> {
}
