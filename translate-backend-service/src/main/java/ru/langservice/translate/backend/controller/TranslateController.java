package ru.langservice.translate.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.langservice.translate.backend.domain.Translation;
import ru.langservice.translate.backend.service.TranslationService;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class TranslateController {

    private final TranslationService translationService;

    @GetMapping("/translations")
    public List<Translation> translations() {
        return translationService.getTranslations();
    }

    @GetMapping("/translations/{id}")
    public Translation translation(@PathVariable Long id) {
        return translationService.getTranslation(id);
    }

    @PostMapping("/translations")
    public Translation createTranslation(@RequestBody Translation translation){
        // TODO getting translation text ...
        translation.setResultText("Translated text!");
        return translationService.saveTranslation(translation);
    }

    @DeleteMapping("/translations/{id}")
    public void deleteTranslation(@PathVariable Long id) {
        translationService.deleteTranslation(id);
    }

}
