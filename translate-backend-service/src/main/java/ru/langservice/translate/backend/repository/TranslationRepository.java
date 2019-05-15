package ru.langservice.translate.backend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.langservice.translate.backend.domain.Translation;

//import java.util.List;

public interface TranslationRepository extends CrudRepository<Translation, Long> {
//    List<Translation> findByLang(String lang);
//    List<Translation> findByUserId(Long userId);
//    List<Translation> findByLangAndUserId(String lang, Long userId);
}
