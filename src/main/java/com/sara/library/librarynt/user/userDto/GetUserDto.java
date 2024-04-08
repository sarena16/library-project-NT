package com.sara.library.librarynt.user.userDto;

public class GetUserDto {

    private long id;
    private String name;
    private String email;
    private String lastName;

    public GetUserDto(long id, String name, String email, String lastName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
    }

    public GetUserDto(long userId, String email) {
        this.id = userId;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}