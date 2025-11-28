package cori1304.hexagonal.user.adapter.in.web;

import cori1304.hexagonal.user.domain.User;
import lombok.Getter;

@Getter
class UserResponse {
    private final Long id;
    private final String email;
    private final String name;

    UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
