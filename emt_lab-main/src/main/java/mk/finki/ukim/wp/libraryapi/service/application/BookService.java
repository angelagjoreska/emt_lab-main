package mk.finki.ukim.wp.libraryapi.service.application;


import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import mk.finki.ukim.wp.libraryapi.model.dto.DisplayBookDto;
import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import mk.finki.ukim.wp.libraryapi.model.enums.BookState;
import mk.finki.ukim.wp.libraryapi.model.projection.BookDetailsProjection;
import mk.finki.ukim.wp.libraryapi.model.projection.BookShortProjection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {

    // Додавање нова книга
    Book create(String name, BookCategory category, Long authorId, Integer availableCopies);

    // Ажурирање на постоечка книга
    Book update(Long id, String name, BookCategory category, Long authorId, Integer availableCopies);

    // Бришење книга
    void deleteById(Long id);

    // Обележување книга како изнајмена
    Book markAsRented(Long id);

    // Сите книги
    List<Book> findAll();

    // Наоѓање книга по id
    Optional<Book> findById(Long id);

    // По категорија
    List<Book> findByCategory(BookCategory category);

    // По состојба
    List<Book> findByState(BookState state);

    List<Book> findBooksBetweenIds(Long a, Long b);

    Page<DisplayBookDto> findAllWithFilters(
            BookCategory category,
            BookState state,
            Long authorId,
            Boolean available,
            int page,
            int size,
            String sortBy
    );

    List<BookShortProjection> findAllShort();
    List<BookDetailsProjection> findAllDetails();


}