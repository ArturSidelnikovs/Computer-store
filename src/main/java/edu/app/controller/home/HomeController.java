package edu.app.controller.home;

import edu.app.model.phone.Computer;
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
@RequestMapping("/home")
public class HomeController {

    private final IService<Computer> computerIService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(IService<Computer> computerIService) {
        this.computerIService = computerIService;
    }


    @GetMapping()
    public String home(Model model) {
        List<Computer> carouselItemActive = new ArrayList<>();
        carouselItemActive.add(computerIService.findById(1L));
        carouselItemActive.add(computerIService.findById(2L));
        carouselItemActive.add(computerIService.findById(3L));
        carouselItemActive.add(computerIService.findById(4L));
        List<Computer> carouselItem = new ArrayList<>();
        carouselItem.add(computerIService.findById(5L));
        carouselItem.add(computerIService.findById(6L));
        carouselItem.add(computerIService.findById(7L));
        carouselItem.add(computerIService.findById(8L));

        carouselItem.removeAll(Collections.singleton(null));
        carouselItemActive.removeAll(Collections.singleton(null));

        model.addAttribute("itemActive", carouselItemActive);
        model.addAttribute("item", carouselItem);

        return "home/home";
    }

}