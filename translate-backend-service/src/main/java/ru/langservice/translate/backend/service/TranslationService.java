package ru.langservice.translate.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.langservice.translate.backend.domain.Translation;
import ru.langservice.translate.backend.exception.NoTranslationFound;
import ru.langservice.translate.backend.repository.TranslationRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TranslationService {

    private final TranslationRepository translationRepository;

    public List<Translation> getTranslations() {
        return (List<Translation>) translationRepository.findAll();
    }

    public Translation saveTranslation(Translation data) {
        return translationRepository.save(data);
    }

    public Translation getTranslation(Long id) {
        return translationRepository.findById(id).orElseThrow(NoTranslationFound::new);
    }

    public void deleteTranslation(Long id) {
        translationRepository.deleteById(id);
    }
}
