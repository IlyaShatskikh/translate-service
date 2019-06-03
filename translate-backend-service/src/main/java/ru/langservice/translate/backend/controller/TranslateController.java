package ru.langservice.translate.backend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.langservice.translate.backend.domain.Translation;
import ru.langservice.translate.backend.domain.User;
import ru.langservice.translate.backend.dto.TranslateResult;
import ru.langservice.translate.backend.exception.NoTranslationFound;
import ru.langservice.translate.backend.service.TranslateService;
import ru.langservice.translate.backend.service.UserService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class TranslateController {
    private final TranslateService translateService;
    private final UserService userService;
    private final RestTemplate restTemplate;

    @GetMapping("/translations")
    public List<Translation> translations(Authentication authentication) {
        User user = userService.findUserByUsername(authentication.getName());
        return translateService.getTranslations(user.getId());
    }

    @GetMapping("/translations/{id}")
    public Translation translation(@PathVariable Long id) {
        return translateService.getTranslation(id);
    }

    @PostMapping("/translations")
    public Translation createTranslation(Authentication authentication, @RequestBody Translation translation){
        log.trace("Getting translation...");

        User user = userService.findUserByUsername(authentication.getName());
        translation.setUser(user);

        TranslateResult translateResult = translateService.doTranslate(restTemplate, translation.getOrigText(), translation.getLang());
        if (translateResult == null || translateResult.getText() == null || translateResult.getText().isEmpty()){
            throw new NoTranslationFound();
        }
        translation.setResultText(String.join("", translateResult.getText()));
        return translateService.saveTranslation(translation);
    }

    @DeleteMapping("/translations/{id}")
    public void deleteTranslation(@PathVariable Long id) {
        translateService.deleteTranslation(id);
    }
}
