package com.codecraftery.Code.craftery.server.side.service;


import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectServiceException;

import java.util.List;

public interface ProjectService {
    ProjectDto findById(Long id) throws ProjectServiceException, ProjectNotFoundException;

    List<ProjectDto> getAllProjects() throws ProjectServiceException;

    ProjectDto addProject(ProjectDto project) throws ProjectCreationException;

    void deleteById(Long id) throws ProjectServiceException, ProjectNotFoundException;
    ProjectDto updateProject(ProjectDto projectDto, Long id) throws ProjectCreationException, ProjectNotFoundException, ProjectServiceException;
}
