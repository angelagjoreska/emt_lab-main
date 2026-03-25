package mk.finki.ukim.wp.libraryapi.repository;


import mk.finki.ukim.wp.libraryapi.model.domain.Book;
import mk.finki.ukim.wp.libraryapi.model.enums.BookCategory;
import mk.finki.ukim.wp.libraryapi.model.enums.BookState;
import mk.finki.ukim.wp.libraryapi.model.projection.BookDetailsProjection;
import mk.finki.ukim.wp.libraryapi.model.projection.BookShortProjection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findByCategory(BookCategory category);
    List<Book> findByState(BookState state);

    List<Book> findAllByIdBetween(Long startId, Long endId);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByCategory(BookCategory category, Pageable pageable);

    Page<Book> findByState(BookState state, Pageable pageable);

    Page<Book> findByAuthor_Id(Long authorId, Pageable pageable);

    Page<Book> findByAvailableCopiesGreaterThan(Integer copies, Pageable pageable);

    List<BookShortProjection> findAllProjectedBy();

    // Овој метод ќе го користиш за ендпоинтот со проширен приказ
    List<BookDetailsProjection> findAllDetailsProjectedBy();

    // Дополнително: Ако сакаш пагинација и со проекција
    Page<BookShortProjection> findProjectedBy(Pageable pageable);

    @EntityGraph(attributePaths = {"author", "author.country"})
    @Override
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);

    // Можеш да го додадеш и на специфичен метод за детален приказ
    @EntityGraph(attributePaths = {"author", "author.country"})
    Optional<Book> findById(Long id);


}
