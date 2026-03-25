package mk.finki.ukim.wp.libraryapi.service.application;

import mk.finki.ukim.wp.libraryapi.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Country create(String name, String continent);

    Country update(Long id, String name, String continent);

    void deleteById(Long id);

    Optional<Country> findById(Long id);

    List<Country> findAll();
}