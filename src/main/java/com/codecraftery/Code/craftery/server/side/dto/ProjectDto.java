package com.codecraftery.Code.craftery.server.side.dto;

import com.codecraftery.Code.craftery.server.side.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {

    private Long id;

    private String name;

    private String projectUrl;

    private String imageUrl;

    private String description;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private List<Category> projectCategories;
}
