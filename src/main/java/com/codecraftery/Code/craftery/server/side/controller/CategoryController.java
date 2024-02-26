package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.dto.CategoryDto;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationException;
import com.codecraftery.Code.craftery.server.side.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories() throws CategoryServiceException {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }


    @PostMapping("/create-category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws CategoryCreationException, ValidationException {
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) throws CategoryNotFoundException, CategoryServiceException {

        categoryService.deleteById(id);
        return ResponseEntity.ok().build();


    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getBlogById(@PathVariable Long id) throws CategoryNotFoundException, CategoryServiceException {

        CategoryDto categoryDto = categoryService.findById(id);

        return ResponseEntity.ok(categoryDto);


    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<CategoryDto> updateBlog(@PathVariable Long id, @RequestBody CategoryDto categoryDto) throws CategoryNotFoundException, CategoryServiceException, ValidationException {

        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
        return ResponseEntity.ok(updatedCategory);

    }
}
