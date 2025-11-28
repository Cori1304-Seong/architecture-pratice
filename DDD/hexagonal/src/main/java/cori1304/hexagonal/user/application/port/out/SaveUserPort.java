package cori1304.hexagonal.user.application.port.out;

import cori1304.hexagonal.user.domain.User;

public interface SaveUserPort {
    User save(User user);
}
