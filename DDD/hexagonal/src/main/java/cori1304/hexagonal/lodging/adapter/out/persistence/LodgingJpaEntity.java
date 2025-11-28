package cori1304.hexagonal.lodging.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lodging")
@Getter
@NoArgsConstructor
class LodgingJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private int maxGuests;
    private boolean isAvailable;
    
    public LodgingJpaEntity(String name, String description, int maxGuests, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.maxGuests = maxGuests;
        this.isAvailable = isAvailable;
    }
}
