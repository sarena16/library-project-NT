package com.sara.library.librarynt.loan.loanDto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CreateLoanDto {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Book ID cannot be null")
    private Long bookId;

    @NotNull(message = "Due date cannot be null")
    private Date dueDate;

    public CreateLoanDto() {
    }

    public CreateLoanDto(Long userId, Long bookId, Date dueDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.dueDate = dueDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

//
//    public BookEntity getBookEntity(BookEntity book) {
//        return book;
//    }
//
//    public UserEntity getUserEntity(UserEntity user) {
//        return user;
//    }




