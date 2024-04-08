package com.sara.library.librarynt.book;

import com.sara.library.librarynt.book.bookDto.CreateBookDto;
import com.sara.library.librarynt.book.bookDto.CreateBookResponseDto;
import com.sara.library.librarynt.book.bookDto.GetBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@PreAuthorize("hasRole('ADMIN')")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<GetBookDto> getAllBooks(){
        return bookService.getAll();

    }
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public GetBookDto getOne(@PathVariable long id){
        return bookService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody CreateBookDto book){
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
