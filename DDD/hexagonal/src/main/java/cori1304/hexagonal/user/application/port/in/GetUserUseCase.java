package cori1304.hexagonal.user.application.port.in;

import cori1304.hexagonal.user.domain.User;

public interface GetUserUseCase {
    User getUserById(Long id);
    User getUserByEmail(String email);
}
