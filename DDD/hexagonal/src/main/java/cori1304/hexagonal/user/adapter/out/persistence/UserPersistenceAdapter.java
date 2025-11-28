package cori1304.hexagonal.user.adapter.out.persistence;

import cori1304.hexagonal.user.application.port.out.LoadUserPort;
import cori1304.hexagonal.user.application.port.out.SaveUserPort;
import cori1304.hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToDomainEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::mapToDomainEntity);
    }

    @Override
    public User save(User user) {
        UserJpaEntity savedUser = userRepository.save(
            userMapper.mapToJpaEntity(user)
        );
        return userMapper.mapToDomainEntity(savedUser);
    }
}
