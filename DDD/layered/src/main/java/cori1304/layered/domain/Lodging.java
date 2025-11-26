package cori1304.layered.domain;

import jakarta.persistence.*;
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
    private Boolean isAvailable;

    public Lodging(String name, String address, String description, BigDecimal pricePerNight, Integer maxGuests) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.maxGuests = maxGuests;
        this.isAvailable = true;
    }
}
