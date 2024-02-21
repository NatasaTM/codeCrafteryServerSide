package com.codecraftery.Code.craftery.server.side.service;


import com.codecraftery.Code.craftery.server.side.model.Project;

import java.util.List;

public interface ProjectService {
    Project getProject(Long id);

    List<Project> getAllProjects();

    Project addProject(Project project);

    void deleteById(Long id);
}
