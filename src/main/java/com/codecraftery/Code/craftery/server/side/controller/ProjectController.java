package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.dto.ProjectDto;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectServiceException;
import com.codecraftery.Code.craftery.server.side.service.CategoryService;
import com.codecraftery.Code.craftery.server.side.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProjectDto>> getProjects() throws ProjectServiceException {

        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping("/create-project")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) throws ProjectCreationException {

        return new ResponseEntity<>(projectService.addProject(projectDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-project/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) throws ProjectNotFoundException, ProjectServiceException {
        projectService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) throws ProjectNotFoundException, ProjectServiceException {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PutMapping("/update-project/{id}")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto, @PathVariable Long id) throws ProjectCreationException, ProjectNotFoundException, ProjectServiceException {
        return ResponseEntity.ok(projectService.updateProject(projectDto, id));
    }
}
