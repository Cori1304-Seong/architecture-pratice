package cori1304.hexagonal.lodging.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Lodging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String address;
    private String description;
    private BigDecimal pricePerNight;
    private Integer maxGuests;
    private Boolean isAvailable = true;
    
    public Lodging(String name, String address, String description, BigDecimal pricePerNight, Integer maxGuests) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.maxGuests = maxGuests;
        this.isAvailable = true;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
