package cori1304.hexagonal.user.adapter.out.persistence;

import cori1304.hexagonal.user.domain.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {
    User mapToDomainEntity(UserJpaEntity user) {
        return new User(
            user.getId(),
            user.getEmail(),
            user.getName()
        );
    }

    UserJpaEntity mapToJpaEntity(User user) {
        UserJpaEntity jpaEntity = new UserJpaEntity(
            user.getEmail(),
            user.getName()
        );
        // ID는 새로운 엔티티 생성시에는 null이어야 함
        return jpaEntity;
    }
}
