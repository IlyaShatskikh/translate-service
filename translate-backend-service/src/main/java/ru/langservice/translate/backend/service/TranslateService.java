package ru.langservice.translate.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.langservice.translate.backend.domain.Translation;
import ru.langservice.translate.backend.dto.TranslateResult;
import ru.langservice.translate.backend.exception.NoTranslationFound;
import ru.langservice.translate.backend.repository.TranslateRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TranslateService {
    private final TranslateRepository translateRepository;
    private final TranslateUriService translateUriService;

    public List<Translation> getTranslations() {
        return (List<Translation>) translateRepository.findAll();
    }

    public Translation saveTranslation(Translation data) {
        return translateRepository.save(data);
    }

    public Translation getTranslation(Long id) {
        return translateRepository.findById(id).orElseThrow(NoTranslationFound::new);
    }

    public void deleteTranslation(Long id) {
        translateRepository.deleteById(id);
    }

    public TranslateResult doTranslate(RestTemplate restTemplate, String text, String lang) {
        LinkedMultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("lang", lang);
        valueMap.add("text", text);
        valueMap.add("format", "plain");

        log.trace("GetTranslate valueMap: {}", valueMap);

        try{
            return restTemplate.getForObject(translateUriService.getTranslateUri(valueMap), TranslateResult.class);
        } catch (Exception e ){
            log.error("No translation data. Caught exception: {}. Exception message: {}", e.getClass().getCanonicalName(), e.getMessage());
            log.warn("Return empty translation result");
            return new TranslateResult();
        }
    }
}
