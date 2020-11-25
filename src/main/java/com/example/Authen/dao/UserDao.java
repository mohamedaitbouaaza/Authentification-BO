package com.example.Authen.dao;

import com.example.Authen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mohamed AIT BOUAAZA
 */

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findBylogin(Object object);

    User findByLogin(String login);

    @Query("select U from User U where U.idUtilisateur=:idUser")
    User findByIdUser(@Param("idUser")long idUser);

    @Query("select U from User U where U.email=:identifiant or U.login=:identifiant")
    User findByLoginOrEmail(@Param("identifiant")String identifiant);

    User findByResetToken(String identifiant);


}