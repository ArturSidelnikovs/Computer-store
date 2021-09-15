package edu.app.initialization;

import edu.app.model.role.RoleEnum;
import edu.app.model.user.User;
import edu.app.service.user_service.IUserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserInitializer {

    private final IUserService<User> userService;


    public UserInitializer(IUserService<User> serviceUser) {
        this.userService = serviceUser;
    }

    public void InitUsers (ConfigurableApplicationContext context) {
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        User user = new User();
        user.setRole(RoleEnum.USER);
        user.setUserName("user");
        user.setPassword(encoder.encode("user"));
        user.setCreatedOn(new Date());

        User admin = new User();
        admin.setRole(RoleEnum.ADMIN);
        admin.setUserName("admin");
        admin.setPassword(encoder.encode("admin"));
        admin.setCreatedOn(new Date());

        userService.save(user);
        userService.save(admin);

    }
}
