package edu.app.initialization;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
@Getter
public class DBInitializer {

    private final UserInitializer userInitializer;
    private final FilmsInitializer filmsInitializer;


    @Value("${project.InitDataBase}")
    private String isNeedToInitDatabase;


    public DBInitializer(UserInitializer userInitializer, FilmsInitializer filmsInitializer) {
        this.userInitializer = userInitializer;
        this.filmsInitializer = filmsInitializer;
    }

    public void initDb(ConfigurableApplicationContext context) {
        if (isNeedToInitDatabase.equals("true")) {
            filmsInitializer.InitFilms();
            userInitializer.InitUsers(context);
        }
    }
}

