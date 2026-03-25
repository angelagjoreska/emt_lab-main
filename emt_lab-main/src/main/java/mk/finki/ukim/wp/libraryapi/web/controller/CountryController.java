package mk.finki.ukim.wp.libraryapi.web.controller;


import mk.finki.ukim.wp.libraryapi.model.domain.Country;
import mk.finki.ukim.wp.libraryapi.model.dto.CreateCountryDto;
import mk.finki.ukim.wp.libraryapi.model.dto.DisplayCountryDto;
import mk.finki.ukim.wp.libraryapi.service.application.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @GetMapping("/{id}")
    public DisplayCountryDto findById(@PathVariable Long id) {
        Country country = countryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
        return DisplayCountryDto.from(country);
    }

    @PostMapping
    public DisplayCountryDto create(@RequestBody CreateCountryDto createCountryDto) {
        Country country = countryService.create(
                createCountryDto.name(),
                createCountryDto.continent()
        );
        return DisplayCountryDto.from(country);
    }

    @PutMapping("/{id}")
    public DisplayCountryDto update(@PathVariable Long id, @RequestBody CreateCountryDto updateCountryDto) {
        Country country = countryService.update(
                id,
                updateCountryDto.name(),
                updateCountryDto.continent()
        );
        return DisplayCountryDto.from(country);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}