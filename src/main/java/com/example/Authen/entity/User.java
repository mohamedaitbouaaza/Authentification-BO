package com.example.Authen.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Mohamed AIT BOUAAZA
 */

@Entity
@Table(name = "TAB_USER")
public class User {

    @Id
    @GeneratedValue(generator = "SEQ_ID_TARB_USER")
    @GenericGenerator(name = "SEQ_ID_TARB_USER", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence", value = "SEQ_ID_TARB_USER") })
    @Column(name = "ID_USER")
    private Long idUtilisateur;

    @Column(name = "NOM")
    private String nomUtilisateur;

    @Column(name = "PRENOM")
    private String prenomUtilisateur;

    @Column(name = "EMAIL", unique = true)
    @NotNull
    private String email;

    @Column(name = "TELPOR")
    private String telephonePortable;

    @Column(name = "LOGIN", unique = true)
    private String login;

    @Column(name = "PASSWD")
    private String password;

    @Column(name = "RESET_TOKEN",unique=true)
    private String resetToken;

    @Column(name = "ROLE")
    private String libelle;

    public User() {

    }

    public User(String name, String username, String password) {
        this.nomUtilisateur = name;
        this.login = username;
        this.password = password;
    }



    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephonePortable() {
        return telephonePortable;
    }

    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

}
