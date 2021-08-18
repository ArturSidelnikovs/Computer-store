package edu.app.controllers;

import edu.app.configurations.security.SecurityUser;
import edu.app.model.user.User;
import edu.app.service.user_service.IUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class LoginController {

    private final IUserService<User> service;
    private final PasswordEncoder encoder;


    public LoginController(IUserService<User> service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }


    @GetMapping ("/login")
   public String login () {
        return "login/Login";
    }

    @GetMapping ("/registration")
    public String registration (Model model) {
        model.addAttribute("User", new User());
        return "registration/Registration";
    }


    @PostMapping ("/registration")
     public String createNewUser (@ModelAttribute ("User") @Valid User user,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration/Registration";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        final boolean userByUsername = service.findUserByUsername(user.getUserName());
        if (userByUsername) {
            return "redirect:/registration?usernameExists=true";
        }
        service.save(user);

        SecurityUser securityUser = new SecurityUser(user);
        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
        return "redirect:/home";

    }
}
