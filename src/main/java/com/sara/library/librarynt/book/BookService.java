package com.sara.library.librarynt.book;

import com.sara.library.librarynt.book.bookDto.CreateBookDto;
import com.sara.library.librarynt.book.bookDto.CreateBookResponseDto;
import com.sara.library.librarynt.book.bookDto.GetBookDto;
import com.sara.library.librarynt.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookDto> getAll(){
        var books= bookRepository.findAll();

        return books.stream().map(this::mapBook).collect(Collectors.toList());
    }

    public GetBookDto getOne(long id){
        var bookEntity = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        return mapBook(bookEntity);
    }

    private GetBookDto mapBook(BookEntity bookEntity){
        return new GetBookDto(bookEntity.getId(), bookEntity.getIsbn(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPublisher(), bookEntity.getPublicationYear(), bookEntity.getAvailableCopies()>0);

    }

    public CreateBookResponseDto create(CreateBookDto book){
        var bookEntity =  new BookEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setAvailableCopies(book.getAvailableCopies());
        bookEntity.setPublicationYear(book.getPublicationYear());

        var newBook= bookRepository.save(bookEntity);

        return new CreateBookResponseDto(newBook.getId(), newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublisher(), newBook.getPublicationYear(), newBook.getAvailableCopies());
    }
    public void delete(long id){
        if (bookRepository.existsById(id)) {
            throw new RuntimeException();
        }
        bookRepository.deleteById(id);
    }
}
