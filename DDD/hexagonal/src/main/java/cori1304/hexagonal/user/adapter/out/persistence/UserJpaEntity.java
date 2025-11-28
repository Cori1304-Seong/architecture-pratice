package cori1304.hexagonal.user.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
class UserJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;

    public UserJpaEntity(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
