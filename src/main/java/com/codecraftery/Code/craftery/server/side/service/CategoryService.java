package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.dto.CategoryDto;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationException;

import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
public interface CategoryService {

    List<CategoryDto> getAllCategories() throws CategoryServiceException;

    CategoryDto addCategory(CategoryDto categoryDto) throws CategoryCreationException, ValidationException;

    CategoryDto findById(Long id) throws CategoryServiceException, CategoryNotFoundException;

    void deleteById(Long id) throws CategoryServiceException, CategoryNotFoundException;

    CategoryDto updateCategory(CategoryDto categoryDto, Long id) throws CategoryServiceException, CategoryNotFoundException, ValidationException;


//    Category findByName(String name);
//    List<Category> findListById(List<Long> ids);

}

