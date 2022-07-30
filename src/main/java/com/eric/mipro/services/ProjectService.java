package com.eric.mipro.services;

import com.eric.mipro.domain.Project;
import com.eric.mipro.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }
}
