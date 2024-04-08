package com.sara.library.librarynt.loan.loanDto;

import com.sara.library.librarynt.book.bookDto.GetBookDto;
import com.sara.library.librarynt.user.userDto.GetUserDto;

import java.util.Date;

public class GetLoanDto {

    private long id;
    private GetUserDto user;
    private GetBookDto book;
    private Date loanDate;
    private Date dueDate;


    public GetLoanDto(long id, Date loanDate, Date dueDate, GetUserDto user, GetBookDto book) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.dueDate = dueDate;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GetUserDto getUser() {
        return user;
    }

    public void setUser(GetUserDto user) {
        this.user = user;
    }

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}
