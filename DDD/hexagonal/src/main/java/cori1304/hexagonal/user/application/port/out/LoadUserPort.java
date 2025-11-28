package cori1304.hexagonal.user.application.port.out;

import cori1304.hexagonal.user.domain.User;
import java.util.Optional;

public interface LoadUserPort {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
