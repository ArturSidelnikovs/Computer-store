package edu.app;

import edu.app.initDb.InitDb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class SpringAppApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringAppApplication.class, args);
        initDb(run);
    }

    private static void initDb(ConfigurableApplicationContext context) {
        InitDb db = context.getBean(InitDb.class);
        db.initDb( context);
    }




}
