package com.sara.library.librarynt.loan.loanDto;

import java.util.List;

public class GetLoansPageResponseDto {
    private List<GetLoanDto> loans;

    private int currentPage;

    private long totalItems;

    private int totalPages;

    private boolean hasMore;

    public List<GetLoanDto> getLoans() {
        return loans;
    }

    public void setLoans(List<GetLoanDto> loans) {
        this.loans = loans;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public GetLoansPageResponseDto(List<GetLoanDto> loans, int currentPage, long totalItems, int totalPages, boolean hasMore) {
        this.loans = loans;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.hasMore = hasMore;
    }
}
