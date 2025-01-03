package com.ashok.library_management_system.service;

import com.ashok.library_management_system.payload.DTO.CategoryDTO;
import com.ashok.library_management_system.payload.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);
}
