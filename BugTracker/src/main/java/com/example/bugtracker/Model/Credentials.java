package com.example.bugtracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credentials")
public class Credentials {
    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "user_id")
    @OneToOne
    @JoinColumn(name="user_id", table = "users")
    private User user;
}
