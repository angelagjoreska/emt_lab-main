package mk.finki.ukim.wp.libraryapi.service.application;

import mk.finki.ukim.wp.libraryapi.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author create(String name, String surname, Long countryId);

    Author update(Long id, String name, String surname, Long countryId);

    void deleteById(Long id);

    Optional<Author> findById(Long id);

    List<Author> findAll();

}
