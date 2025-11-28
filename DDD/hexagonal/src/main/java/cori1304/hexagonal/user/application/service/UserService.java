package cori1304.hexagonal.user.application.service;

import cori1304.hexagonal.user.application.port.in.CreateUserUseCase;
import cori1304.hexagonal.user.application.port.in.GetUserUseCase;
import cori1304.hexagonal.user.application.port.out.LoadUserPort;
import cori1304.hexagonal.user.application.port.out.SaveUserPort;
import cori1304.hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class UserService implements GetUserUseCase, CreateUserUseCase {
    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;

    @Override
    public User getUserById(Long id) {
        return loadUserPort.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return loadUserPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return saveUserPort.save(user);
    }
}
