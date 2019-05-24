package ru.langservice.translate.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.langservice.translate.backend.domain.Role;
import ru.langservice.translate.backend.domain.User;
import ru.langservice.translate.backend.exception.UserAlreadyExistsAuthenticationException;
import ru.langservice.translate.backend.repository.UserRepository;
import ru.langservice.translate.backend.security.UserPrincipals;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.debug("User by username '{}' not found", username);
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipals(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User addUser(final User user) {
        User userFromRepository = userRepository.findByUsername(user.getUsername());
        if (userFromRepository != null ) {
            log.debug("User already exists. Username: {}", user.getUsername());
            throw new UserAlreadyExistsAuthenticationException(user.getUsername());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));

        log.debug("Save user: {}", user);
        return userRepository.save(user);
    }
}
