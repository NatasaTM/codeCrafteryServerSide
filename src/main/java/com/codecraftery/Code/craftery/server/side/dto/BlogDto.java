package com.codecraftery.Code.craftery.server.side.dto;

import com.codecraftery.Code.craftery.server.side.model.BlogCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDto {

    private Long id;

    private String title;

    private String text;

    private File image;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private BlogCategory blogCategory;
}
