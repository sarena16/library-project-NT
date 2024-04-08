package com.sara.library.librarynt.loan.loanDto;


import jakarta.validation.constraints.NotNull;

import java.awt.print.Book;
import java.util.Date;

public class CreateLoanDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long bookId;
    @NotNull
    private Date dueDate;


    public CreateLoanDto(Long userId, Long bookId, Date dueDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.dueDate = dueDate;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }



    //
//    public BookEntity getBookEntity(BookEntity book) {
//        return book;
//    }
//
//    public UserEntity getUserEntity(UserEntity user) {
//        return user;
//    }


}

