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

    Book create(String name, BookCategory category, Long authorId, Integer availableCopies);

    Book update(Long id, String name, BookCategory category, Long authorId, Integer availableCopies);

    void deleteById(Long id);

    Book markAsRented(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    List<Book> findByCategory(BookCategory category);

    List<Book> findByState(BookState state);

    List<Book> findBooksBetweenIds(Long a, Long b);

    List<Book> getMostPopularBooks(int limit);
    List<mk.finki.ukim.wp.libraryapi.model.domain.Author> getMostPopularAuthors(int limit);


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