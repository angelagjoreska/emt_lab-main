package mk.finki.ukim.wp.libraryapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "library_category_stats")
@Immutable
@Getter
public class LibraryCategoryStats {
    @Id
    private String category;
    private Long totalBooks;
    private Long totalAvailableCopies;
    private Long booksNotGoodCondition;
}