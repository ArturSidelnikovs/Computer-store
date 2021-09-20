package edu.app.initDb;

import edu.app.initDb.initComputers.InitComputers;
import edu.app.initDb.initUsers.InitUsers;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;;

@Component
@ConfigurationProperties(prefix = "project")
@Getter
public class InitDb {
    private final InitComputers initComputers;
    private final InitUsers initUsers;

    @Value("${project.initDb}")
    private String initDb;

    public InitDb(InitComputers initComputers, InitUsers initUsers) {
        this.initComputers = initComputers;
        this.initUsers = initUsers;

    }

    public void initDb(ConfigurableApplicationContext context) {
        if (initDb.equals("true")) {
            initComputers.initComputers();
            initUsers.initUsers(context);
        }
    }


}
