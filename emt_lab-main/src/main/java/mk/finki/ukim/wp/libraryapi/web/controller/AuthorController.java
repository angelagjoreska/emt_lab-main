package mk.finki.ukim.wp.libraryapi.web.controller;


import mk.finki.ukim.wp.libraryapi.model.domain.Author;
import mk.finki.ukim.wp.libraryapi.model.dto.CreateAuthorDto;
import mk.finki.ukim.wp.libraryapi.model.dto.DisplayAuthorDto;
import mk.finki.ukim.wp.libraryapi.service.application.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @GetMapping("/get/{id}")
    public DisplayAuthorDto findById(@PathVariable Long id) {
        Author author = authorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        return DisplayAuthorDto.from(author);
    }

    @PostMapping
    public DisplayAuthorDto create(@RequestBody CreateAuthorDto createAuthorDto) {
        Author author = authorService.create(
                createAuthorDto.name(),
                createAuthorDto.surname(),
                createAuthorDto.countryId()
        );
        return DisplayAuthorDto.from(author);
    }

    @PutMapping("/update/{id}")
    public DisplayAuthorDto update(@PathVariable Long id, @RequestBody CreateAuthorDto updateAuthorDto) {
        Author author = authorService.update(
                id,
                updateAuthorDto.name(),
                updateAuthorDto.surname(),
                updateAuthorDto.countryId()
        );
        return DisplayAuthorDto.from(author);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}