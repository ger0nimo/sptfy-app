package com.sptfy.web.app.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class User {

    public User() {
    }

    public User(String username, String password, String role, String registrationDate, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, boolean premium) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.registrationDate = registrationDate;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.premium = premium;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String role;// TODO change to list to make multiple roles available

    private String registrationDate;

    private boolean enabled;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    private boolean premium;

}

