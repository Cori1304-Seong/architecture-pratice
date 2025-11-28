package cori1304.hexagonal.user.adapter.in.web;

import cori1304.hexagonal.user.application.port.in.CreateUserUseCase;
import cori1304.hexagonal.user.application.port.in.GetUserUseCase;
import cori1304.hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
class UserController {
    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = getUserUseCase.getUserById(id);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping("/by-email")
    ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {
        User user = getUserUseCase.getUserByEmail(email);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @PostMapping
    ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = new User(
            null,
            request.getEmail(),
            request.getName()
        );
        User createdUser = createUserUseCase.createUser(user);
        return ResponseEntity.ok(new UserResponse(createdUser));
    }
}
