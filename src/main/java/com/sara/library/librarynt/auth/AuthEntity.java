package com.sara.library.librarynt.auth;

import com.sara.library.librarynt.commonTypes.UserRole;
import com.sara.library.librarynt.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "auth", schema = "library")
public class AuthEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne
    @JoinColumn(name ="user_id", nullable = false)
    private UserEntity user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
