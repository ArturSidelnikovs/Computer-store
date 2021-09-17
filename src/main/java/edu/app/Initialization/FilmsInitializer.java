package edu.app.initialization;

import edu.app.model.film.Film;
import edu.app.service.IService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmsInitializer {

    private final IService<Film> service;

    public FilmsInitializer(IService<Film> service) {
        this.service = service;
    }

    public void InitFilms () {

        List<Film> list = new ArrayList<>();
        Film film1 = new Film();
        film1.setFilmDirector("Родольфо Саягес");
        film1.setName("Don't Breathe 2");
        film1.setGenre("Триллеры, Ужасы");
        list.add(film1);

        Film film2 = new Film();
        film2.setFilmDirector("Фердинандо Чито Филомарино");
        film2.setName("Beckett");
        film2.setGenre("Боевики, Драмы, Криминальные, Триллеры");
        list.add(film2);

        Film film3 = new Film();
        film3.setFilmDirector("Джеймс Ганн");
        film3.setName("The Suicide Squad");
        film3.setGenre("Боевики, Комедии, Фантастические");
        list.add(film3);

        saveFilms(list);

    }

    private void saveFilms(List<Film> films) {
        films.forEach(service::save);
    }
}

