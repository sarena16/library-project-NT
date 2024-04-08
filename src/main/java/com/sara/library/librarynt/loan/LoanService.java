package com.sara.library.librarynt.loan;

import com.sara.library.librarynt.book.bookDto.GetBookDto;
import com.sara.library.librarynt.book.BookEntity;
import com.sara.library.librarynt.loan.loanDto.CreateLoanDto;
import com.sara.library.librarynt.loan.loanDto.CreateLoanResponseDto;
import com.sara.library.librarynt.loan.loanDto.GetLoanDto;
import com.sara.library.librarynt.loan.loanDto.GetLoansPageResponseDto;
import com.sara.library.librarynt.service.OwnershipService;
import com.sara.library.librarynt.user.UserEntity;
import com.sara.library.librarynt.repositories.AuthRepository;
import com.sara.library.librarynt.repositories.BookRepository;
import com.sara.library.librarynt.repositories.LoanRepository;
import com.sara.library.librarynt.repositories.UserRepository;
import com.sara.library.librarynt.errors.BookNotFound;
import com.sara.library.librarynt.errors.LoanNotFound;
import com.sara.library.librarynt.errors.UserNotFound;
import com.sara.library.librarynt.user.userDto.GetUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class LoanService extends OwnershipService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;





    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, AuthRepository authRepository) {
       super(authRepository);
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;

    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #loanDto.userId)")

    public CreateLoanResponseDto create(CreateLoanDto loanDto){

        UserEntity user = userRepository.findById(loanDto.getUserId()).orElseThrow(()-> UserNotFound.createWithId(loanDto.getUserId()));

        BookEntity book = bookRepository.findById(loanDto.getBookId()).orElseThrow(()-> BookNotFound.create(loanDto.getBookId()));

        LoanEntity loan = new LoanEntity();
        loan.setBookEntity(book);
        loan.setUserEntity(user);
        loan.setLoanDate(new Date(System.currentTimeMillis()));
        loan.setDueDate(loanDto.getDueDate());
        loanRepository.save(loan);

        return new CreateLoanResponseDto(loan.getId(), loan.getLoanDate(), loan.getDueDate(), loan.getUserEntity().getId(), loan.getBookEntity().getId());

    }

    @PostAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, returnedObject.userId) ")
    public GetLoanDto getOneById(long id){
        LoanEntity loan = loanRepository.findById(id).orElseThrow(()-> LoanNotFound.create(id));
        return mapLoan(loan);

    }
    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #userId) ")
    public GetLoansPageResponseDto getAll(Long userId, int page, int size){
        Page<LoanEntity> loansPage;

        Pageable pageable = PageRequest.of(page,size);

        if(userId == null){
            loansPage = loanRepository.findAll(pageable);
        }else {
            loansPage= loanRepository.findByUserId(userId,pageable);
        }

        List<GetLoanDto> loansDto =  loansPage.getContent().stream().map(this::mapLoan).toList();
        return new GetLoansPageResponseDto(loansDto,loansPage.getNumber(),loansPage.getTotalElements(),loansPage.getTotalPages(),loansPage.hasNext());
    }

    private GetLoanDto mapLoan(LoanEntity loan){
        GetUserDto user = new GetUserDto(loan.getUserEntity().getId(), loan.getUserEntity().getName(), loan.getUserEntity().getLastName(), loan.getUserEntity().getEmail());
        GetBookDto book = new GetBookDto(
                loan.getBookEntity().getId(),
                loan.getBookEntity().getIsbn(),
                loan.getBookEntity().getTitle(),
                loan.getBookEntity().getAuthor(),
                loan.getBookEntity().getPublisher(),
                loan.getBookEntity().getPublicationYear(),
                loan.getBookEntity().getAvailableCopies()>0);

        return new GetLoanDto(loan.getId(), loan.getLoanDate(), loan.getDueDate(), user,book);


    }

//    public List<GetLoanDto> getAll() {
//        var loans = loanRepository.findAll();
//
//        return loans.stream().map((loan) -> new GetLoanDto(
//                loan.getId(),
//                loan.getBookEntity().getId(),
//                loan.getUserEntity().getId(),
//                loan.getLoanDate(),
//                loan.getDueDate(),
//                loan.getReturnDate()
//        )).toList();
//    }
//
//    public  GetLoanDto getOne(Long id) {
//        var loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
//
//        return new GetLoanDto(
//                loan.getId(),
//                loan.getBookEntity().getId(),
//                loan.getUserEntity().getId(),
//                loan.getLoanDate(),
//                loan.getDueDate(),
//                loan.getReturnDate()
//        );
//    }
//
//    public CreateLoanResponseDto create(CreateLoanDto loan) {
//        var loanEntity = new LoanEntity();
//
//        loanEntity.setBookEntity(loan.getBookEntity());
//        loanEntity.setUserEntity(loan.getUserEntity());
//        loanEntity.setLoanDate(loan.getLoanDate());
//        loanEntity.setDueDate(loan.getDueDate());
//        loanEntity.setReturnDate(loan.getReturnDate());
//
//        var newLoan = loanRepository.save(loanEntity);
//
//        return new CreateLoanResponseDto(
//                newLoan.getId(),
//                newLoan.getBookEntity().getId(),
//                newLoan.getUserEntity().getId(),
//                newLoan.getLoanDate(),
//                newLoan.getDueDate(),
//                newLoan.getReturnDate()
//        );
//    }
//
//    public void delete(Long id) {
//        if (!loanRepository.existsById(id)) {
//            throw new RuntimeException();
//        }
//        loanRepository.deleteById(id);
//    }
}
