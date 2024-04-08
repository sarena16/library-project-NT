package com.sara.library.librarynt.user.userDto;

import org.openapitools.jackson.nullable.JsonNullable;

public class PatchUserDto {
    private JsonNullable<String> name;
    private JsonNullable<String> lastname;
    private JsonNullable<String> email;

    public JsonNullable<String> getName() {
        return name;
    }

    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getLastname() {
        return lastname;
    }

    public void setLastname(JsonNullable<String> lastname) {
        this.lastname = lastname;
    }

    public JsonNullable<String> getEmail() {
        return email;
    }

    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }

    public PatchUserDto(JsonNullable<String> name, JsonNullable<String> lastname, JsonNullable<String> email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
}
