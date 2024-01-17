package com.marinabits.energietechs.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marinabits.energietechs.repository.entity.Language;

import java.util.Arrays;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record UserResponse(@JsonProperty String id,
                           @JsonProperty String username,
                           @JsonProperty String firstName,
                           @JsonProperty String lastName,
                           @JsonProperty String[] authorities,
                           @JsonProperty String refreshToken,
                           @JsonProperty String accessToken,
                           @JsonProperty Language language) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse userResponse = (UserResponse) o;
        return Objects.equals(this.id, userResponse.id) &&
                Objects.equals(this.username, userResponse.username) &&
                Objects.equals(this.firstName, userResponse.firstName) &&
                Objects.equals(this.lastName, userResponse.lastName) &&
                Objects.equals(this.refreshToken, userResponse.refreshToken) &&
                Objects.equals(this.accessToken, userResponse.accessToken) &&
                Arrays.equals(this.authorities, userResponse.authorities) &&
                Objects.equals(this.language, userResponse.language);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.id, this.username, this.firstName, this.lastName, this.language);
        result = 31 * result + Arrays.hashCode(this.authorities);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().concat(" { id= %s, username=  %s, firstName= %s, lastName= %s , authorities= %s," +
                        " refreshToken= %s,accessToken= %s,language= %s }")
                .formatted(this.id, this.username, this.firstName, this.lastName, Arrays.toString(this.authorities), this.refreshToken,
                        this.accessToken, this.language);
    }
}
