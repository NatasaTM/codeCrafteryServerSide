package com.codecraftery.Code.craftery.server.side.mapper;

import com.codecraftery.Code.craftery.server.side.dto.CategoryDto;
import com.codecraftery.Code.craftery.server.side.model.Category;

/**
 * @author Natasa Todorov Markovic
 */
public class CategoryMapper {
    public static CategoryDto mapCategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryDto;
    }

    public static Category mapCategoryDtoToCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
        return category;
    }
}
