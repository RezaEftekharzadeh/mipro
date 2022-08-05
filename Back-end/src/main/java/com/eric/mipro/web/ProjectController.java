package com.eric.mipro.web;

import com.eric.mipro.advice.exception.ProjectNotFoundException;
import com.eric.mipro.domain.Project;
import com.eric.mipro.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@Valid @RequestBody Project.ProjectBuilder project) {
        return new ResponseEntity<>(projectService.saveOrUpdate(project), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProjectByProjectId(@Valid @PathVariable Long id) throws ProjectNotFoundException {
        return new ResponseEntity<>(projectService.findProjectById(id), HttpStatus.OK);
    }

    @GetMapping("/identifier/{identifier}")
    public ResponseEntity<?> findProjectByProjectIdentifier(@Valid @PathVariable String identifier) throws ProjectNotFoundException {
        return new ResponseEntity<>(projectService.findProjectByIdentifier(identifier), HttpStatus.OK);
    }

    @GetMapping("/allproject")
    public Iterable<Project> findAllProjects() {
        return projectService.findAllProject();
    }

    @DeleteMapping("/{id}")
    public void deleteProjectByid(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }

/*    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@RequestBody Project.ProjectBuilder project, @PathVariable Long id) {
        Optional<Project> projectById = projectService.findProjectById(id);


        return new ResponseEntity<>(projectService.saveOrUpdate(pbfromProject), HttpStatus.OK);
    }*/

}
