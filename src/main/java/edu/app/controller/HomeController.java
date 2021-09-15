package edu.app.controller;

import edu.app.model.film.Film;
import edu.app.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping ("/home")
public class HomeController {


    private final IService <Film> filmService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    public HomeController(IService<Film> filmService) {
        this.filmService = filmService;
    }


    @GetMapping()
    public String home(Model model) {

        List<Film> carouselItem = new ArrayList<>();
        carouselItem.add(filmService.findById(1l));
        carouselItem.add(filmService.findById(2l));
        carouselItem.add(filmService.findById(3l));
        carouselItem.add(filmService.findById(4l));

        carouselItem.removeAll(Collections.singleton(null));

        model.addAttribute("item", carouselItem);
        return "home/home";
    }
}



