package mk.finki.ukim.wp.libraryapi.service.application.listeners;

import mk.finki.ukim.wp.libraryapi.model.events.BookRentedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookRentedListener {

    private static final Logger logger = LoggerFactory.getLogger(BookRentedListener.class);

    @EventListener
    public void onBookRented(BookRentedEvent event) {
        String title = event.getBook().getTitle();
        int available = event.getBook().getAvailableCopies();


        logger.info("EVENT: Book '{}' has been successfully rented.", title);


        if (available == 0) {
            logger.warn("ALERT: Book '{}' is now OUT OF STOCK!", title);
        }
    }
}
