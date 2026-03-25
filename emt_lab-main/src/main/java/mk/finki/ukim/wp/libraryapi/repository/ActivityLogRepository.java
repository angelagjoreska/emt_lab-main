package mk.finki.ukim.wp.libraryapi.repository;

import mk.finki.ukim.wp.libraryapi.model.domain.ActivityLog;
import mk.finki.ukim.wp.libraryapi.model.domain.Author;
import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    // najpopularen knigi preku JOIN so tabelata Book preku naslovot
    @Query("SELECT b FROM Book b JOIN ActivityLog al ON b.title = al.bookTitle " +
            "WHERE al.eventType = 'RENT' " +
            "GROUP BY b " +
            "ORDER BY COUNT(al) DESC")
    List<Book> findMostPopularBooks(Pageable pageable);

    // najpopularen avtori preku JOIN od Log sp Book so Author
    @Query("SELECT b.author FROM Book b JOIN ActivityLog al ON b.title = al.bookTitle " +
            "WHERE al.eventType = 'RENT' " +
            "GROUP BY b.author " +
            "ORDER BY COUNT(al) DESC")
    List<Author> findMostPopularAuthors(Pageable pageable);
}