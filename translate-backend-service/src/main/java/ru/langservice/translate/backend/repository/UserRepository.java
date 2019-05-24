package ru.langservice.translate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.langservice.translate.backend.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
}
