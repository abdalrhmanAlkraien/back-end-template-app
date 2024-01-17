package com.marinabits.energietechs.mapper;

import com.marinabits.energietechs.api.model.response.LanguageResponse;
import com.marinabits.energietechs.infra.util.UuidUtil;
import com.marinabits.energietechs.repository.entity.Language;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        imports = UuidUtil.class)
public abstract class LanguageMapper {

    public abstract Language toEntity(LanguageResponse languageResponse);

    public abstract LanguageResponse toView(Language language);

}
