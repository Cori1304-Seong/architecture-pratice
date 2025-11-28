package cori1304.hexagonal.user.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class CreateUserRequest {
    private String email;
    private String name;
}
