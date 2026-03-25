package mk.finki.ukim.wp.libraryapi.service.application.impl;

import mk.finki.ukim.wp.libraryapi.model.domain.Author;
import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import mk.finki.ukim.wp.libraryapi.model.dto.DisplayBookDto;
import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import mk.finki.ukim.wp.libraryapi.model.enums.BookState;
import mk.finki.ukim.wp.libraryapi.model.events.BookRentedEvent;
import mk.finki.ukim.wp.libraryapi.model.projection.BookDetailsProjection;
import mk.finki.ukim.wp.libraryapi.model.projection.BookShortProjection;
import mk.finki.ukim.wp.libraryapi.repository.AuthorRepository;
import mk.finki.ukim.wp.libraryapi.repository.BookRepository;
import mk.finki.ukim.wp.libraryapi.service.application.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final ApplicationEventPublisher eventPublisher;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher eventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Book create(String name, BookCategory category, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));

        Book book = new Book();
        book.setTitle(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        book.setState(BookState.GOOD);

        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String name, BookCategory category, Long authorId, Integer availableCopies) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));

        book.setTitle(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


    public Book markAsRented(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            book = bookRepository.save(book);


            eventPublisher.publishEvent(new BookRentedEvent(this, book));

            return book;
        } else {
            throw new IllegalStateException("No available copies");
        }
    }



    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByCategory(BookCategory category) {
        return bookRepository.findByCategory(category);
    }

    @Override
    public List<Book> findByState(BookState state) {
        return bookRepository.findByState(state);
    }



    public List<Book> findBooksBetweenIds(Long a, Long b) {
        return bookRepository.findAllByIdBetween(a, b);
    }



    @Override
    public Page<DisplayBookDto> findAllWithFilters(
            BookCategory category,
            BookState state,
            Long authorId,
            Boolean available,
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Book> result;

        if (category != null) {
            result = bookRepository.findByCategory(category, pageable);
        } else if (state != null) {
            result = bookRepository.findByState(state, pageable);
        } else if (authorId != null) {
            result = bookRepository.findByAuthor_Id(authorId, pageable);
        } else if (available != null && available) {
            result = bookRepository.findByAvailableCopiesGreaterThan(0, pageable);
        } else {
            result = bookRepository.findAll(pageable);
        }

        return result.map(DisplayBookDto::from);
    }


    @Override
    public List<BookShortProjection> findAllShort() {
        // Овде го повикуваш методот што го напишавме во BookRepository
        return bookRepository.findAllProjectedBy();
    }

    @Override
    public List<BookDetailsProjection> findAllDetails() {
        // Овде го повикуваш вториот метод од BookRepository
        return bookRepository.findAllDetailsProjectedBy();
    }





}