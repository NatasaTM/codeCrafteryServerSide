package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectServiceException;
import com.codecraftery.Code.craftery.server.side.service.CategoryService;
import com.codecraftery.Code.craftery.server.side.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProjectController {
    private ProjectService projectService;
    private CategoryService categoryService;

    public ProjectController(ProjectService projectService, CategoryService categoryService) {
        this.projectService = projectService;
        this.categoryService = categoryService;
    }
    @GetMapping("/projects")
    public List<ProjectDto> getAllProjects() throws ProjectServiceException {
        List<ProjectDto> projectDtos = projectService.getAllProjects();
        return  projectDtos;
    }
    @PostMapping("/create-project")
    public ProjectDto saveProject(){
        return null;
    }
}
