package mk.finki.ukim.wp.libraryapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OutOfStockEventRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private String bookTitle;
    private LocalDateTime eventTimestamp;

    public OutOfStockEventRecord(Long bookId, String bookTitle) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.eventTimestamp = LocalDateTime.now();
    }
}