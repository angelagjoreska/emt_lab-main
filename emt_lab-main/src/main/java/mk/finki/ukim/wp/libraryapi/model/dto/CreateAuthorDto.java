package mk.finki.ukim.wp.libraryapi.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.finki.ukim.wp.libraryapi.model.domain.Author;
import mk.finki.ukim.wp.libraryapi.model.domain.Country;

public record CreateAuthorDto(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Surname is required")
        String surname,
        @NotNull(message = "Country ID is required")
        Long countryId
) {
    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
