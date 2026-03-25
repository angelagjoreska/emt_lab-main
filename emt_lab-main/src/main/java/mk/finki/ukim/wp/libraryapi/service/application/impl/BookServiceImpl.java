package mk.finki.ukim.wp.libraryapi.service.application.impl;

import mk.finki.ukim.wp.libraryapi.model.domain.ActivityLog;
import mk.finki.ukim.wp.libraryapi.model.domain.Author;
import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import mk.finki.ukim.wp.libraryapi.model.dto.DisplayBookDto;
import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import mk.finki.ukim.wp.libraryapi.model.enums.BookState;
import mk.finki.ukim.wp.libraryapi.model.events.BookRentedEvent;
import mk.finki.ukim.wp.libraryapi.model.projection.BookDetailsProjection;
import mk.finki.ukim.wp.libraryapi.model.projection.BookShortProjection;
import mk.finki.ukim.wp.libraryapi.repository.ActivityLogRepository;
import mk.finki.ukim.wp.libraryapi.repository.AuthorRepository;
import mk.finki.ukim.wp.libraryapi.repository.BookRepository;
import mk.finki.ukim.wp.libraryapi.service.application.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final ApplicationEventPublisher eventPublisher;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ActivityLogRepository activityLogRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           ApplicationEventPublisher eventPublisher,
                           ActivityLogRepository activityLogRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.eventPublisher = eventPublisher;
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public Book create(String name, BookCategory category, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        Book book = new Book(name, category, author, availableCopies);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String name, BookCategory category, Long authorId, Integer availableCopies) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

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

    @Override
    @Transactional
    public Book markAsRented(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    if (book.getAvailableCopies() <= 0) {
                        throw new IllegalStateException("No available copies");
                    }
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    Book savedBook = bookRepository.save(book);

                    activityLogRepository.save(new ActivityLog(savedBook.getTitle(), "RENT"));
                    eventPublisher.publishEvent(new BookRentedEvent(this, savedBook));

                    return savedBook;
                })
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
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

    @Override
    public List<Book> findBooksBetweenIds(Long a, Long b) {
        return bookRepository.findAllByIdBetween(a, b);
    }

    @Override
    public List<Book> getMostPopularBooks(int limit) {
        return activityLogRepository.findMostPopularBooks(PageRequest.of(0, limit));
    }

    @Override
    public List<Author> getMostPopularAuthors(int limit) {
        return activityLogRepository.findMostPopularAuthors(PageRequest.of(0, limit));
    }

    @Override
    public Page<DisplayBookDto> findAllWithFilters(BookCategory category, BookState state, Long authorId, Boolean available, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Book> booksPage = bookRepository.findAll(pageable);
        return booksPage.map(DisplayBookDto::from);
    }

    @Override
    public List<BookShortProjection> findAllShort() {
        return bookRepository.findAllProjectedBy();
    }

    @Override
    public List<BookDetailsProjection> findAllDetails() {
        return bookRepository.findAllDetailsProjectedBy();
    }
}