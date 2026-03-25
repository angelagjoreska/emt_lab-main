package mk.finki.ukim.wp.libraryapi.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import mk.finki.ukim.wp.libraryapi.model.domain.Author;
import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;

public record CreateBookDto(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Category is required")
        BookCategory category,
        @NotNull(message = "Author ID is required")
        Long authorId,
        @Min(value = 1, message = "Available copies must be at least 1")
        Integer availableCopies
) {
    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
