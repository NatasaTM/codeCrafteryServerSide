package com.codecraftery.Code.craftery.server.side.mapper;

import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.model.Project;

/**
 * @author Natasa Todorov Markovic
 */
public class ProjectMapper {

    public static ProjectDto mapProjectToProjectDto(Project project) {
        ProjectDto projectDto = ProjectDto.builder()
                .id(project.getId())
                .projectUrl(project.getProjectUrl())
                .name(project.getName())
                .description(project.getDescription())
                .projectCategories(project.getProjectCategories())
                .createdOn(project.getCreatedOn())
                .updatedOn(project.getUpdatedOn())
                .imageUrl(project.getImageUrl())
                .build();
        return projectDto;
    }

    public static Project mapProjectDtoToProject(ProjectDto projectDto) {
        Project project = Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .projectUrl(projectDto.getProjectUrl())
                .projectCategories(projectDto.getProjectCategories())
                .imageUrl(projectDto.getImageUrl())
                .build();
        return project;
    }
}
