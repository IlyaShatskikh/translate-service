package ru.langservice.translate.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.langservice.translate.backend.dto.GetLangs;
import ru.langservice.translate.backend.exception.NoLangsAvailableException;

import java.util.HashMap;

@Slf4j
@Service
@AllArgsConstructor
public class LanguageService {
    private final TranslateUriService translateUriService;

    public GetLangs getDefaultLangs(){
        HashMap<String, String> langs = new HashMap<>(2);
        langs.put("en", "English");
        langs.put("ru", "Russian");

        GetLangs getLangs = new GetLangs();
        getLangs.setLangs(langs);

        return getLangs;
    }

    public GetLangs getLangs(RestTemplate restTemplate) {
        GetLangs getLangs;
        try{
            getLangs = restTemplate.getForObject(translateUriService.getLangsUri(), GetLangs.class);
        } catch (Exception e){
            log.error("No langs. Caught exception: {}. Exception message: {}", e.getClass().getCanonicalName(), e.getMessage());
            throw new NoLangsAvailableException(e);
        }

        log.trace("Langs: {}", getLangs);
        return getLangs;
    }
}
