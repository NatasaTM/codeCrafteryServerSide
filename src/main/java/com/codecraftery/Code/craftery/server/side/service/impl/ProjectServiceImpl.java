package com.codecraftery.Code.craftery.server.side.service.impl;


import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectServiceException;
import com.codecraftery.Code.craftery.server.side.model.Project;
import com.codecraftery.Code.craftery.server.side.repository.ProjectRepository;
import com.codecraftery.Code.craftery.server.side.service.ProjectService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codecraftery.Code.craftery.server.side.mapper.ProjectMapper.mapProjectDtoToProject;
import static com.codecraftery.Code.craftery.server.side.mapper.ProjectMapper.mapProjectToProjectDto;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    private final Validator validator;


    @Override
    public ProjectDto findById(Long id) throws ProjectServiceException, ProjectNotFoundException {
        try {
            Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found"));
            ProjectDto projectDto = mapProjectToProjectDto(project);
            return projectDto;
        } catch (DataAccessException e) {
            logger.error("Error finding project with ID " + id, e);
            throw new ProjectServiceException("Error finding project with ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<ProjectDto> getAllProjects() throws ProjectServiceException {
        try {

            List<ProjectDto> projectDtos = projectRepository.findAll().stream()
                    .map(project -> mapProjectToProjectDto(project)).collect(Collectors.toList());

            return projectDtos;
        } catch (DataAccessException ex) {
            logger.error("Error while retrieving projects from the database", ex);
            throw new ProjectServiceException("Failed to retrieve blogs", ex);
        }
    }

    @Override
    public ProjectDto addProject(ProjectDto projectDto) throws ProjectCreationException {
        Set<ConstraintViolation<Project>> violations = getConstraintViolations(projectDto);
        if (!violations.isEmpty()) {
            logger.error("ProjectValidation");
            throw new ProjectCreationException(buildValidationErrorMessage(violations));

        }
        try {
            Project project = mapProjectDtoToProject(projectDto);
            Project savedProject = projectRepository.save(project);
            if (savedProject == null) {
                throw new ProjectCreationException("Error saving project to database: ");
            }
            return mapProjectToProjectDto(savedProject);
        } catch (DataAccessException e) {
            logger.error("Error occurred while adding project to database" + e);
            throw new ProjectCreationException("Error saving project to database: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ProjectServiceException, ProjectNotFoundException {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project with ID " + id + " not found");
        }
        try {
            projectRepository.deleteById(id);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new ProjectServiceException("Error while deleting project", ex);
        } catch (Exception e) {
            logger.error("Failed to delete project" + e);
            throw new ProjectServiceException("Failed to delete project" + e.getMessage() + e);
        }

    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto, Long id) throws ProjectCreationException, ProjectNotFoundException, ProjectServiceException {
        Set<ConstraintViolation<Project>> violations = getConstraintViolations(projectDto);
        if (!violations.isEmpty()) {
            logger.error("ProjectValidation");
            throw new ProjectCreationException(buildValidationErrorMessage(violations));
        }

        try {
            Project project = projectRepository.findById(id).get();
            if (project == null) {
                throw new ProjectNotFoundException("Project with ID " + id + " not found");
            }

            project.setName(projectDto.getName());
            project.setProjectUrl(projectDto.getProjectUrl());
            project.setProjectCategories(projectDto.getProjectCategories());
            project.setDescription(projectDto.getDescription());

            return mapProjectToProjectDto(projectRepository.save(project));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ProjectServiceException("Error updating project: " + e.getMessage(), e);
        }
    }

    private Set<ConstraintViolation<Project>> getConstraintViolations(ProjectDto projectDto) {
        Project validateProject = mapProjectDtoToProject(projectDto);
        Set<ConstraintViolation<Project>> violations = validator.validate(validateProject); // Validate the Blog object
        return violations;
    }

    private String buildValidationErrorMessage(Set<ConstraintViolation<Project>> violations) {
        StringBuilder errorMessage = new StringBuilder("Validation errors:");
        for (ConstraintViolation<Project> violation : violations) {
            errorMessage.append("<br>- ").append(violation.getPropertyPath()).append(": ").append(violation.getMessage());
        }
        return errorMessage.toString();
    }
}
