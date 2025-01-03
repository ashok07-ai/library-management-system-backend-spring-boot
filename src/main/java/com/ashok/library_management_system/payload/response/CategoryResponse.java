package com.ashok.library_management_system.payload.response;

import com.ashok.library_management_system.payload.DTO.CategoryDTO;
import java.util.List;

public class CategoryResponse {
    private List<CategoryDTO> responseObject;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;

    public CategoryResponse(List<CategoryDTO> responseObject, Integer pageNumber, Integer pageSize, Long totalElements, Integer totalPages, boolean lastPage) {
        this.responseObject = responseObject;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.lastPage = lastPage;
    }

    public CategoryResponse(){

    }

    public List<CategoryDTO> getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(List<CategoryDTO> responseObject) {
        this.responseObject = responseObject;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}