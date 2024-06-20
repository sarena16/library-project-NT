package com.sara.library.librarynt.loan;

import com.sara.library.librarynt.loan.loanDto.CreateLoanDto;
import com.sara.library.librarynt.loan.loanDto.CreateLoanResponseDto;
import com.sara.library.librarynt.loan.loanDto.GetLoanDto;
import com.sara.library.librarynt.loan.loanDto.GetLoansPageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@PreAuthorize("hasRole('ADMIN')")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody @Validated CreateLoanDto createLoanDto) {
        CreateLoanResponseDto createdLoan = loanService.create(createLoanDto);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLoanDto> getOneById(@PathVariable long id) {
        GetLoanDto loanDto = loanService.getOneById(id);
        return new ResponseEntity<>(loanDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<GetLoansPageResponseDto> getAll(@RequestParam(required = false) Long userId,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "7") int size) {
        GetLoansPageResponseDto loansPageResponseDto = loanService.getAll(userId, page, size);
        return new ResponseEntity<>(loansPageResponseDto, HttpStatus.OK);
    }
}


//    private final LoanService loanService;
//
//    @Autowired
//    public LoanController(LoanService loanService) {
//        this.loanService = loanService;
//    }
//
//    @GetMapping
//    public List<GetLoanDto> getAllLoans(){
//        return loanService.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public GetLoanDto getOne(@PathVariable long id){
//        return loanService.getOne(id);
//    }
//
//    @PostMapping
//    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody CreateLoanDto loan) {
//        var newLoan =  loanService.create(loan);
//        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable long id) {
//        loanService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
