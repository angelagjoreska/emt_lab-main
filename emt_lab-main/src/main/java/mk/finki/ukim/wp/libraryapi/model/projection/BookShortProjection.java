package mk.finki.ukim.wp.libraryapi.model.projection;

import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import mk.finki.ukim.wp.libraryapi.model.enums.BookState;

public interface BookShortProjection {
    Long getId();
    String getTitle(); // Мора да биде getTitle бидејќи полето е title
    BookCategory getCategory();
    BookState getState();
    Integer getAvailableCopies();
}