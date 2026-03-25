package mk.finki.ukim.wp.libraryapi.service.application.impl;


import mk.finki.ukim.wp.libraryapi.model.domain.Author;
import mk.finki.ukim.wp.libraryapi.model.domain.Country;
import mk.finki.ukim.wp.libraryapi.repository.AuthorRepository;
import mk.finki.ukim.wp.libraryapi.repository.CountryRepository;
import mk.finki.ukim.wp.libraryapi.service.application.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        Author author = new Author(name, surname, country);
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long id, String name, String surname, Long countryId) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}