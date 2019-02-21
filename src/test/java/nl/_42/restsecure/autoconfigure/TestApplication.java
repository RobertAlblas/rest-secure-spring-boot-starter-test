package nl._42.restsecure.autoconfigure;

import nl._42.restsecure.autoconfigure.authentication.AbstractUserDetailsService;
import nl._42.restsecure.autoconfigure.authentication.RegisteredUser;
import nl._42.restsecure.autoconfigure.authentication.UserBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TestApplication.class).run(args);
    }

    @Bean
    public UserBuilder userBuilder() {
        return new UserBuilder();
    }

    @Bean
    public AbstractUserDetailsService userDetailsService() {
        return new AbstractUserDetailsService() {
            @Override
            protected RegisteredUser findUserByUsername(String username) {
                return userBuilder().user();
            }
        };
    }
}
