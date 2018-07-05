package com.sptfy.web.app.Model;


import lombok.*;

import javax.persistence.*;

@Entity

@Data//lombok
//@NoArgsConstructor
//@AllArgsConstructor
public class Users {

    public Users() {
    }

    public Users(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;


    private String password;


    private String role;


}

