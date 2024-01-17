package com.marinabits.energietechs.api.controller;

import com.marinabits.energietechs.repository.entity.Language;
import com.marinabits.energietechs.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/languages")
public class LanguagesResource {

    private final LanguageService business;

    @GetMapping
    public List<Language> getLanguages() {
        return business.getLanguages();
    }
}
