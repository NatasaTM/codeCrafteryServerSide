package com.codecraftery.Code.craftery.server.side.service;


import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectServiceException;

import java.util.List;

public interface ProjectService {
    ProjectDto findById(Long id);

    List<ProjectDto> getAllProjects() throws ProjectServiceException;

    ProjectDto addProject(ProjectDto project);

    void deleteById(Long id);
    ProjectDto updateProject(Long id);
}
