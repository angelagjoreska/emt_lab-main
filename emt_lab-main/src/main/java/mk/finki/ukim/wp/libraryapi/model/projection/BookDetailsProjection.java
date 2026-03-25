package mk.finki.ukim.wp.libraryapi.model.projection;

import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import mk.finki.ukim.wp.libraryapi.model.enums.BookState;
import org.springframework.beans.factory.annotation.Value;

public interface BookDetailsProjection {
    Long getId();
    String getTitle();
    BookCategory getCategory();
    BookState getState();
    Integer getAvailableCopies();

    // Ги спојуваме името и презимето на авторот од поврзаниот ентитет Author
    @Value("#{target.author.name + ' ' + target.author.surname}")
    String getAuthorFullName();

    // Ја земаме државата на авторот (Author -> Country -> Name)
    @Value("#{target.author.country.name}")
    String getAuthorCountryName();
}