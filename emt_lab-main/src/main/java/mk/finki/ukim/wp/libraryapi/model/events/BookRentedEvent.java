package mk.finki.ukim.wp.libraryapi.model.events;

import lombok.Getter;
import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import org.springframework.context.ApplicationEvent;

@Getter
public class BookRentedEvent extends ApplicationEvent {
    private final Book book;

    public BookRentedEvent(Object source, Book book) {
        super(source);
        this.book = book;
    }
}