package org.sopt.Seminar.domain.user.repository;

import java.util.Optional;
import org.sopt.Seminar.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
}
