package com.marinabits.energietechs.service;

import com.marinabits.energietechs.repository.entity.Language;
import com.marinabits.energietechs.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository repository;

    public List<Language> getLanguages() {

        List<Language> languages = new ArrayList<>();
        repository.findAll().forEach(languages::add);
        return languages;
    }
}
