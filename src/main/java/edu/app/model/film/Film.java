package edu.app.model.film;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "films")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Film implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    Long id;

    @Size(min = 1, max = 50)
    @NotEmpty(message = "Film name should not be empty")
    private String name;

    @Size(min = 5, max = 50)
    private String genre;

    @Size(min = 5, max = 50)
    private String filmDirector;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> imageUrls = new ArrayList<>();

    public Film() { }

    public void addImage(Film film, String imgLink) {
        film.getImageUrls().add(imgLink);
    }



}
