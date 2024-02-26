package com.codecraftery.Code.craftery.server.side.service;


import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationException;

import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
public interface ProjectService {
    ProjectDto findById(Long id) throws ProjectServiceException, ProjectNotFoundException;

    List<ProjectDto> getAllProjects() throws ProjectServiceException;

    ProjectDto addProject(ProjectDto project) throws ProjectCreationException, ValidationException;

    void deleteById(Long id) throws ProjectServiceException, ProjectNotFoundException;

    ProjectDto updateProject(ProjectDto projectDto, Long id) throws ProjectNotFoundException, ProjectServiceException, ValidationException;
}
