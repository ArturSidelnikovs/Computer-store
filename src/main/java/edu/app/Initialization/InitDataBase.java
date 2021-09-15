package edu.app.Initialization;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
@Getter
public class InitDataBase {

    private final InitUser initUser;
    private final InitFilms initFilms;


    @Value("${project.InitDataBase}")
    private String InitDataBase;


    public InitDataBase(InitUser initUser, InitFilms initFilms) {
        this.initUser = initUser;
        this.initFilms = initFilms;
    }

    public void initDb(ConfigurableApplicationContext context) {
        if (InitDataBase.equals("true")) {
            initFilms.InitFilms();
            initUser.InitUsers(context);
        }
    }
}

