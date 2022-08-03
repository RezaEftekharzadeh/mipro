package com.eric.mipro.services;

import com.eric.mipro.advice.exception.ProjectNotFoundException;
import com.eric.mipro.domain.Project;
import com.eric.mipro.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }

    public Optional<Project> findProjectById(Long id) throws ProjectNotFoundException {
        Optional<Project> byId = projectRepository.findById(id);
        if(byId.isPresent()){
            return byId;
        }
         throw new ProjectNotFoundException("project does not exist");
    }

    public Project findProjectByIdentifier(String identifier) throws ProjectNotFoundException {
        Project project = projectRepository.findByProjectIdentifier(identifier);
        if(project != null){
            return project;
        }
         throw new ProjectNotFoundException("project does not exist");
    }

    public Iterable<Project> findAllProject(){
        return projectRepository.findAll();
    }

}
