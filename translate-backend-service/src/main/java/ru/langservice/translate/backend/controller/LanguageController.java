package ru.langservice.translate.backend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.langservice.translate.backend.dto.GetLangs;
import ru.langservice.translate.backend.exception.NoLangsAvailableException;
import ru.langservice.translate.backend.service.LanguageService;

@Slf4j
@RestController
@AllArgsConstructor
public class LanguageController {
    private final LanguageService languageService;
    private final RestTemplate restTemplate;

    @GetMapping("/languages")
    public GetLangs getLanguages(){
        try{
            return languageService.getLangs(restTemplate);
        } catch (NoLangsAvailableException e){
            log.warn("Using default languages");
            return languageService.getDefaultLangs();
        }
    }
}
