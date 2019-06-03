package ru.langservice.translate.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.langservice.translate.backend.domain.User;
import ru.langservice.translate.backend.service.UserService;

@RestController
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.addUser(user);
    }
}
