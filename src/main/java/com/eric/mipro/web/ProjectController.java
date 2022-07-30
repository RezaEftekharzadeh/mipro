package com.eric.mipro.web;

import com.eric.mipro.domain.Project;
import com.eric.mipro.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/")
    public ResponseEntity<Project> createNewProject(@Valid @RequestBody Project.ProjectBuilder project) {
        project.projectName(project.getProjectName());
        project.projectIdentifier(project.getProjectIdentifier());
        Project newProject = project.build();
        return new ResponseEntity<>(projectService.saveOrUpdate(newProject), HttpStatus.CREATED);
    }
}
