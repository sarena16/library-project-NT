package com.sara.library.librarynt.errors.bookError;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyExists {
    public static ResponseStatusException createWithIsbn(String Isbn){
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Book with isbn: %s already exists.",Isbn));
    }
}
