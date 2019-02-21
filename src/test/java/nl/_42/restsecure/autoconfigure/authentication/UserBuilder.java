package nl._42.restsecure.autoconfigure.authentication;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserBuilder {

    public RegisteredUser user() {
        return new RegisteredUser() {

            @Override
            public String getUsername() {
                return "username";
            }

            @Override
            public String getPassword() {
                return "password";
            }

            @Override
            public Set<String> getAuthorities() {
                return new HashSet<String>(Arrays.asList("ROLE_ADMIN"));
            }

            @Override
            public boolean isAccountExpired() {
                return false;
            }

            @Override
            public boolean isAccountLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsExpired() {
                return false;
            }
        };
    }
}
