package Application.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${app.lang.param:lang}")
    private String langParam;


}
