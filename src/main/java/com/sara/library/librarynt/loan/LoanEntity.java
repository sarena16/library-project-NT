package com.sara.library.librarynt.loan;

import com.sara.library.librarynt.book.BookEntity;
import com.sara.library.librarynt.user.UserEntity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans", schema = "library")
public class LoanEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Basic
    @Column(name = "loan_date", nullable = false)
    private Date loanDate;

    @Basic
    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Basic
    @Column(name = "return_date")
    private Date returnDate;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookEntity getBookEntity() {
        return book;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.book = bookEntity;
    }

    public UserEntity getUserEntity() {
        return user;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.user = userEntity;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}