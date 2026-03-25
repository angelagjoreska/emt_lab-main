package mk.finki.ukim.wp.libraryapi.service.application.impl;

import mk.finki.ukim.wp.libraryapi.model.domain.Country;
import mk.finki.ukim.wp.libraryapi.repository.CountryRepository;
import mk.finki.ukim.wp.libraryapi.service.application.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name, continent);
        return countryRepository.save(country);
    }

    @Override
    public Country update(Long id, String name, String continent) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        country.setName(name);
        country.setContinent(continent);
        return countryRepository.save(country);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}