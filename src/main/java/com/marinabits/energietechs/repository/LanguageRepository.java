package com.marinabits.energietechs.repository;

import com.marinabits.energietechs.repository.entity.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {

}
