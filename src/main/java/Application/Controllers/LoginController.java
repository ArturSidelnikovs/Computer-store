package Application.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class LoginController {

    public String index () {return "redirect:/products";}

    @GetMapping("/login")
    public ModelAndView login(@RequestParam Optional<String> error) {
        final ModelAndView modelAndView = new ModelAndView("login");
        if (error.isPresent()) {
            modelAndView.addObject("errorMessage", "Error occurred");
        }
        return modelAndView;
    }






}
