package mk.finki.ukim.wp.libraryapi.repository;

import mk.finki.ukim.wp.libraryapi.model.domain.BookDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsViewRepository extends JpaRepository<BookDetailsView, Long> {

}