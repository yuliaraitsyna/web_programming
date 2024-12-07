package by.bsu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "access_level")
    private String accessLevel;

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String s) {
        username = s;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String level) {
        accessLevel = level;
    }
}
