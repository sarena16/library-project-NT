package com.sara.library.librarynt.loan;

import com.sara.library.librarynt.loan.loanDto.CreateLoanDto;
import com.sara.library.librarynt.loan.loanDto.CreateLoanResponseDto;
import com.sara.library.librarynt.loan.loanDto.GetLoanDto;
import com.sara.library.librarynt.loan.loanDto.GetLoansPageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@PreAuthorize("hasRole('ADMIN')")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody @Validated CreateLoanDto loanEntity){
        var newLoan = loanService.create(loanEntity);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLoanDto> getOnebyId(@PathVariable long id){
        GetLoanDto dto = loanService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<GetLoansPageResponseDto> getAll(@RequestParam(required = false) Long userId,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "7") int size){
        GetLoansPageResponseDto dto = loanService.getAll(userId, page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
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
