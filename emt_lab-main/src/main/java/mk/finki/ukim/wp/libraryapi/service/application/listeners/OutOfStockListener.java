package mk.finki.ukim.wp.libraryapi.service.application.listeners;

import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import mk.finki.ukim.wp.libraryapi.model.domain.OutOfStockEventRecord;
import mk.finki.ukim.wp.libraryapi.model.events.BookRentedEvent;
import mk.finki.ukim.wp.libraryapi.repository.OutOfStockRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OutOfStockListener {

    private static final Logger logger = LoggerFactory.getLogger(OutOfStockListener.class);
    private final OutOfStockRecordRepository recordRepository;

    public OutOfStockListener(OutOfStockRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @EventListener
    public void handleBookRented(BookRentedEvent event) {
        Book book = event.getBook();

        if (book.getAvailableCopies() == 0) {

            recordRepository.save(new OutOfStockEventRecord(book.getId(), book.getTitle()));


            logger.warn("CRITICAL: Book '{}' (ID: {}) is now OUT OF STOCK. Event recorded in database.",
                    book.getTitle(), book.getId());
        }
    }
}