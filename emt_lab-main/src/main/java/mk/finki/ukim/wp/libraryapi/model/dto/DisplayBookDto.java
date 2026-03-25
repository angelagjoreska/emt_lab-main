package mk.finki.ukim.wp.libraryapi.model.dto;

import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import mk.finki.ukim.wp.libraryapi.model.enums.BookState;

import java.time.LocalDateTime;
import java.util.List;

public record DisplayBookDto(
        Long id,
        String name,
        BookCategory category,
        DisplayAuthorDto author,
        BookState state,
        Integer availableCopies,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getTitle(),
                book.getCategory(),
                DisplayAuthorDto.from(book.getAuthor()),
                book.getState(),
                book.getAvailableCopies(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).toList();
    }
}