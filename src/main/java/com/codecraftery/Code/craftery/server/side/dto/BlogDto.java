package com.codecraftery.Code.craftery.server.side.dto;

import com.codecraftery.Code.craftery.server.side.model.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDto {

    private Long id;

    private String title;

    private String text;

    private String imageUrl;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private List<Category> blogCategories;
}
