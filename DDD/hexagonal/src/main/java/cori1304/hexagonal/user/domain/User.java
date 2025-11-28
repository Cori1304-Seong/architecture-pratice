package cori1304.hexagonal.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
    private final Long id;
    private final String email;
    private final String name;
}
