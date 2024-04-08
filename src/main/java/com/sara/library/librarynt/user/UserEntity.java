package com.sara.library.librarynt.user;

import com.sara.library.librarynt.loan.LoanEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "library")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "lastName")
    private String lastName;

    @Basic
    @Column(name = "livingAddress")
    private String livingAddress;



    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<LoanEntity> loans;

    public UserEntity() {

    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }


    public List<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
    }

    public UserEntity(long id, String name, String email, String lastName, String livingAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.livingAddress = livingAddress;
    }
}
