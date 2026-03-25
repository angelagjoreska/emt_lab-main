package mk.finki.ukim.wp.libraryapi.model.domain;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import  mk.finki.ukim.wp.libraryapi.model.enums.BookState;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String title;

    @Enumerated(EnumType.STRING)
    public BookCategory category;

    @ManyToOne
    public Author author;

    @Enumerated(EnumType.STRING)
    public BookState state;

    @Column(name = "available_copies")
    public Integer availableCopies;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Book(String title, BookCategory category, Author author, Integer availableCopies) {
        this.title = title;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.state = BookState.GOOD;
    }
}
