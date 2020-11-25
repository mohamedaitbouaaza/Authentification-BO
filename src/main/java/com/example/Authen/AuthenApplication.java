package com.example.Authen;

import com.example.Authen.configuration.DefaultUserConfiguration;
import com.example.Authen.dao.UserDao;
import com.example.Authen.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author Mohamed AIT BOUAAZA
 */

@SpringBootApplication
public class AuthenApplication {

    private static final Logger LOG = LoggerFactory.getLogger("AdviceApplication");

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(AuthenApplication.class, args);

        AuthenApplication app = new AuthenApplication();
        app.AddUserAdmin(ctx);
    }
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris")); // It will set Europe/Paris timezone
    }


    /* Enregistrer un utilisateur par défaut */
    private void AddUserAdmin(ConfigurableApplicationContext ctx) {

        UserDao userRepo = ctx.getBean(UserDao.class);
        DefaultUserConfiguration userConfig = ctx.getBean(DefaultUserConfiguration.class);


        if (userRepo.findByLoginOrEmail(userConfig.getUsername()) == null) {
            userRepo.save(createUser(userConfig.getUsername(), userConfig.getPassword()));
        }
    }

    private User createUser(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User admin = new User();
        admin.setLogin(username);
        admin.setLibelle("ADMIN");
        admin.setPassword(encoder.encode(password));
        LOG.info("user created" + ": " + username);
        return admin;
    }

}
