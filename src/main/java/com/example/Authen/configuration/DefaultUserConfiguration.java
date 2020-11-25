package com.example.Authen.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mohamed AIT BOUAAZA
 */

@Configuration
public class DefaultUserConfiguration {
    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}


