package mk.finki.ukim.wp.libraryapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "book_details_view")
@Immutable
@Getter
public class BookDetailsView {
    @Id
    private Long id;
    private String title;
    private String category;
    private String state;
    private Integer availableCopies;
    private String authorFullName;
    private String countryName;
}