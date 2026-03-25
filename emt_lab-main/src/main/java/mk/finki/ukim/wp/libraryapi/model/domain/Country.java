package mk.finki.ukim.wp.libraryapi.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;
    public String continent;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

}
