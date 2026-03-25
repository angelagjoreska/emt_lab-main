package mk.finki.ukim.wp.libraryapi.web.controller;

import mk.finki.ukim.wp.libraryapi.model.domain.*;
import mk.finki.ukim.wp.libraryapi.model.dto.CreateBookDto;
import mk.finki.ukim.wp.libraryapi.model.dto.DisplayBookDto;
import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import mk.finki.ukim.wp.libraryapi.model.enums.BookState;
import mk.finki.ukim.wp.libraryapi.model.projection.BookDetailsProjection;
import mk.finki.ukim.wp.libraryapi.model.projection.BookShortProjection;
import mk.finki.ukim.wp.libraryapi.repository.ActivityLogRepository;
import mk.finki.ukim.wp.libraryapi.repository.BookDetailsViewRepository;
import mk.finki.ukim.wp.libraryapi.repository.LibraryCategoryStatsRepository;
import mk.finki.ukim.wp.libraryapi.repository.OutOfStockRecordRepository;
import mk.finki.ukim.wp.libraryapi.service.application.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookDetailsViewRepository bookDetailsViewRepository;
    private final LibraryCategoryStatsRepository libraryCategoryStatsRepository;
    private final OutOfStockRecordRepository outOfStockRepository;
    private final ActivityLogRepository activityLogRepository;

    public BookController(BookService bookService,
                          BookDetailsViewRepository bookDetailsViewRepository,
                          LibraryCategoryStatsRepository libraryCategoryStatsRepository,
                          OutOfStockRecordRepository outOfStockRepository,
                          ActivityLogRepository activityLogRepository) {
        this.bookService = bookService;
        this.bookDetailsViewRepository = bookDetailsViewRepository;
        this.libraryCategoryStatsRepository = libraryCategoryStatsRepository;
        this.outOfStockRepository = outOfStockRepository;
        this.activityLogRepository = activityLogRepository;
    }

    // 1. СТАТИЧНИ ПАТЕКИ И ФИЛТРИ (Одат најгоре)

    @GetMapping("/filter")
    public Page<DisplayBookDto> findAllWithFilters(
            @RequestParam(required = false) BookCategory category,
            @RequestParam(required = false) BookState state,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Boolean available,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy // ОВА СМЕНИ ГО ВО 'title'
    ) {
        return bookService.findAllWithFilters(category, state, authorId, available, page, size, sortBy);
    }

    @GetMapping("/projection/short")
    public List<BookShortProjection> getShort() {
        return bookService.findAllShort();
    }

    @GetMapping("/projection/details")
    public List<BookDetailsProjection> getDetails() {
        return bookService.findAllDetails();
    }

    @GetMapping("/database-view")
    public List<BookDetailsView> findAllFromView() {
        return bookDetailsViewRepository.findAll();
    }

    @GetMapping("/statistics")
    public List<LibraryCategoryStats> getStats() {
        return libraryCategoryStatsRepository.findAll();
    }

    @PostMapping("/statistics/refresh")
    public ResponseEntity<Void> refresh() {
        libraryCategoryStatsRepository.refreshStats();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activity-log")
    public Page<ActivityLog> getActivityLog(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("eventTime").descending());
        return activityLogRepository.findAll(pageable);
    }

    @GetMapping("/out-of-stock-log")
    public List<OutOfStockEventRecord> getOutOfStockLog() {
        return outOfStockRepository.findAll();
    }

    // 2. ЕНДПОИНТИ СО ПРОМЕНЛИВИ {id} (Одат подолу)

    @GetMapping("/{id}")
    public DisplayBookDto findById(@PathVariable Long id) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return DisplayBookDto.from(book);
    }

    @PostMapping("/{id}/rent")
    public DisplayBookDto markAsRented(@PathVariable Long id) {
        Book book = bookService.markAsRented(id);
        return DisplayBookDto.from(book);
    }

    @PutMapping("/{id}")
    public DisplayBookDto update(@PathVariable Long id, @RequestBody CreateBookDto updateBookDto) {
        Book book = bookService.update(
                id,
                updateBookDto.name(),
                updateBookDto.category(),
                updateBookDto.authorId(),
                updateBookDto.availableCopies()
        );
        return DisplayBookDto.from(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // 3. ОСТАНАТО

    @GetMapping("/all-list") // Го преименував за да не се меша со пагинацијата
    public List<DisplayBookDto> findAllList() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @PostMapping
    public DisplayBookDto create(@RequestBody CreateBookDto createBookDto) {
        Book book = bookService.create(
                createBookDto.name(),
                createBookDto.category(),
                createBookDto.authorId(),
                createBookDto.availableCopies()
        );
        return DisplayBookDto.from(book);
    }
}