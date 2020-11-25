package com.example.Authen.security;

import com.example.Authen.dao.UserDao;
import com.example.Authen.entity.User;
import com.example.Authen.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mohamed AIT BOUAAZA
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserDao utilisateurCabinetDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = utilisateurCabinetDao.findBylogin(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Utilisateur non trouvé avec nom  ou Nom d'utilisateur : " + username)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = utilisateurCabinetDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}
