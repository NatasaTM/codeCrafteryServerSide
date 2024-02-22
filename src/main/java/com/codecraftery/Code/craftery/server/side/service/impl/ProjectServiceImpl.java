package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.model.Project;
import com.codecraftery.Code.craftery.server.side.repository.ProjectRepository;
import com.codecraftery.Code.craftery.server.side.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codecraftery.Code.craftery.server.side.mapper.ProjectMapper.mapProjectDtoToProject;
import static com.codecraftery.Code.craftery.server.side.mapper.ProjectMapper.mapProjectToProjectDto;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDto getProject(Long id) {
        ProjectDto projectDto = mapProjectToProjectDto(projectRepository.findById(id).get());
        return projectDto;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            ProjectDto projectDto = mapProjectToProjectDto(projects.get(i));
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    @Override
    public ProjectDto addProject(ProjectDto project) {
        Project p = projectRepository.save(mapProjectDtoToProject(project));
        ProjectDto projectDto = mapProjectToProjectDto(p);
        return projectDto;
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);

    }
}
