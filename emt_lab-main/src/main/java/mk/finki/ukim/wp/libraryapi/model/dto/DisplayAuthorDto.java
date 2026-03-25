package mk.finki.ukim.wp.libraryapi.model.dto;



import mk.finki.ukim.wp.libraryapi.model.domain.Author;

import java.util.List;

public record DisplayAuthorDto(
        Long id,
        String name,
        String surname,
        DisplayCountryDto country
) {
    public static DisplayAuthorDto from(Author author) {
        return new DisplayAuthorDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                DisplayCountryDto.from(author.getCountry())
        );
    }

    public static List<DisplayAuthorDto> from(List<Author> authors) {
        return authors.stream().map(DisplayAuthorDto::from).toList();
    }
}