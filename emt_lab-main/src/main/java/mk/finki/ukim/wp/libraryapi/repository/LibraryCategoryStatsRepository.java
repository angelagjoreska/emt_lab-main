package mk.finki.ukim.wp.libraryapi.repository;

import mk.finki.ukim.wp.libraryapi.model.domain.LibraryCategoryStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LibraryCategoryStatsRepository extends JpaRepository<LibraryCategoryStats, String> {

    @Transactional
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW library_category_stats", nativeQuery = true)
    void refreshStats();
}