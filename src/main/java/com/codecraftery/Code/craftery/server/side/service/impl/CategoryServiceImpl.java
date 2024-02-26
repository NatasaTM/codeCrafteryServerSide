package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.dto.CategoryDto;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationErrorMessageBuilder;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationException;
import com.codecraftery.Code.craftery.server.side.model.Category;
import com.codecraftery.Code.craftery.server.side.repository.CategoryRepository;
import com.codecraftery.Code.craftery.server.side.service.CategoryService;
import com.codecraftery.Code.craftery.server.side.validation.impl.CategoryValidator;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codecraftery.Code.craftery.server.side.mapper.CategoryMapper.mapCategoryDtoToCategory;
import static com.codecraftery.Code.craftery.server.side.mapper.CategoryMapper.mapCategoryToCategoryDto;

/**
 * @author Natasa Todorov Markovic
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryValidator categoryValidator;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final ValidationErrorMessageBuilder<Category> validationErrorMessageBuilder;


    @Override
    public List<CategoryDto> getAllCategories() throws CategoryServiceException {
        try {
            List<CategoryDto> categoryDtos = categoryRepository.findAll().stream()
                    .map(category -> mapCategoryToCategoryDto(category))
                    .collect(Collectors.toList());

            return categoryDtos;
        } catch (DataAccessException ex) {
            logger.error("Error while retrieving categories from the database", ex);
            throw new CategoryServiceException("Failed to retrieve categories", ex);
        }
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) throws CategoryCreationException, ValidationException {
        Set<ConstraintViolation<Category>> violations = categoryValidator.validate(mapCategoryDtoToCategory(categoryDto));
        if (!violations.isEmpty()) {
            logger.error("BlogValidation");
            throw new ValidationException(validationErrorMessageBuilder.buildValidationErrorMessage(violations));

        }
        try {
            Category category = mapCategoryDtoToCategory(categoryDto);
            Category savedCategory = categoryRepository.save(category);
            if (savedCategory == null) {
                throw new CategoryCreationException("Error saving category to database: ");
            }
            return mapCategoryToCategoryDto(savedCategory);
        } catch (DataAccessException e) {
            logger.error("Error occurred while adding category to database" + e);
            throw new CategoryCreationException("Error saving category to database: " + e.getMessage(), e);
        }

    }

    @Override
    public CategoryDto findById(Long id) throws CategoryServiceException, CategoryNotFoundException {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new CategoryNotFoundException("Blog with ID " + id + " not found"));
            CategoryDto categoryDto = mapCategoryToCategoryDto(category);
            return categoryDto;
        } catch (DataAccessException e) {
            // Log the error if needed
            logger.error("Error finding category with ID " + id, e);
            throw new CategoryServiceException("Error finding category with ID ");
        }
    }

    @Override
    public void deleteById(Long id) throws CategoryServiceException, CategoryNotFoundException {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category with ID " + id + " not found");
        }
        try {
            categoryRepository.deleteById(id);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new CategoryServiceException("Error while deleting category", ex);
        } catch (Exception e) {
            logger.error("Failed to delete category" + e);
            throw new CategoryServiceException("Failed to delete category");
        }


    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) throws CategoryServiceException, CategoryNotFoundException, ValidationException {
        Set<ConstraintViolation<Category>> violations = categoryValidator.validate(mapCategoryDtoToCategory(categoryDto));
        if (!violations.isEmpty()) {
            logger.error("CategoryValidation");
            throw new ValidationException(validationErrorMessageBuilder.buildValidationErrorMessage(violations));
        }

        try {
            Category category = categoryRepository.findById(id).get();
            if (category == null) {
                throw new CategoryNotFoundException("Category with ID " + id + " not found");
            }

            category.setName(categoryDto.getName());

            return mapCategoryToCategoryDto(categoryRepository.save(category));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new CategoryServiceException("Error updating category: " + e.getMessage(), e);
        }
    }


}
