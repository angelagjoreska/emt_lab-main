package mk.finki.ukim.wp.libraryapi.service.application.listeners;

import mk.finki.ukim.wp.libraryapi.model.domain.ActivityLog;
import mk.finki.ukim.wp.libraryapi.model.events.BookRentedEvent;
import mk.finki.ukim.wp.libraryapi.repository.ActivityLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ActivityLogListener {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @EventListener
    public void handleBookRented(BookRentedEvent event) {
       
        ActivityLog log = new ActivityLog(
                event.getBook().getTitle(),
                "BOOK_RENTED"
        );
        activityLogRepository.save(log);
    }
}