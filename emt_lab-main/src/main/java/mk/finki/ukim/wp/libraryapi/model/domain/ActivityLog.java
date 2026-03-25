package mk.finki.ukim.wp.libraryapi.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookTitle;
    private LocalDateTime eventTime;
    private String eventType;

    public ActivityLog(String bookTitle, String eventType) {
        this.bookTitle = bookTitle;
        this.eventTime = LocalDateTime.now();
        this.eventType = eventType;
    }
}