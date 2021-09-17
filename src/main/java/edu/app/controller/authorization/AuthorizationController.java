package edu.app.controller.authorization;

import edu.app.config.security.SecurityUser;
import edu.app.model.user.User;
import edu.app.service.userService.IUserService;
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
public class AuthorizationController {

    private final IUserService<User> service;
    private final PasswordEncoder encoder;

    public AuthorizationController(IUserService<User> service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String login() {

        return "authorization/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "registration/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("newUser") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration/registration";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        boolean userByUsername = service.findUserByUsername(user.getUserName());
        boolean userByEmail = service.findUserByEmail(user.getEmail());
        if (userByUsername && userByEmail) {
            return "redirect:/registration?usernameExists=true&&emailExists=true";
        } else if (userByUsername) {
            return "redirect:/registration?usernameExists=true";
        } else if (userByEmail) {
            return "redirect:/registration?emailExists=true";
        }
        service.save(user);

        SecurityUser securityUser = new SecurityUser(user);
        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(token);

        return "redirect:/home";
    }

}