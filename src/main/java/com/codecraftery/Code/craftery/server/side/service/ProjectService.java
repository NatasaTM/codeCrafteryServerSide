package com.codecraftery.Code.craftery.server.side.service;


import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.model.Project;

import java.util.List;

public interface ProjectService {
    ProjectDto getProject(Long id);

    List<ProjectDto> getAllProjects();

    ProjectDto addProject(ProjectDto project);

    void deleteById(Long id);
}
