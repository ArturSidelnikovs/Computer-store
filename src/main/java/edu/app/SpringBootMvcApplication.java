package edu.app;
import edu.app.Initialization.InitDataBase;
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
        InitDataBase initDataBase = context.getBean(InitDataBase.class);
        initDataBase.initDb(context);
    }
}

