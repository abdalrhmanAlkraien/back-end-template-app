package com.marinabits.energietechs.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record LanguageResponse(
        @JsonProperty String id,
        @JsonProperty String name) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageResponse languageResponse = (LanguageResponse) o;
        return Objects.equals(this.id, languageResponse.id) &&
                Objects.equals(this.name, languageResponse.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.id, this.name);
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().concat(" { id= %s, name=  %s }")
                .formatted(this.id, this.name);
    }
}