package Application;
import Application.Entity.Roles.RoleEntity;
import Application.Entity.Roles.Enum.RoleNameEnum;
import Application.Repository.RoleRepository.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootMvcApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringBootMvcApplication.class, args);
        initDatabase(context);
    }

    private static void initDatabase(ConfigurableApplicationContext context) {
        final RoleRepository repository = context.getBean(RoleRepository.class);
        saveIfNotPresent (repository, RoleNameEnum.ADMIN);
        saveIfNotPresent (repository, RoleNameEnum.USER);

//      RegistrationModel defaultAdmin = RegistrationModel.fromLogin("admin");
//		UserModel userModel = context.getBean(UserService.class).initAdmin(defaultAdmin);
}

    private static void saveIfNotPresent(RoleRepository repository, RoleNameEnum user) {
        if (!repository.findByName(user).isPresent()) {
            RoleEntity roleEntity = buildRoleEntity(user);
            repository.save(roleEntity);
        }

    }

    private static RoleEntity buildRoleEntity(RoleNameEnum user) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(user);
        return roleEntity;
    }

}
