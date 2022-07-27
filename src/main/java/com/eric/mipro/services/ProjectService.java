package com.eric.mipro.services;

import com.eric.mipro.domain.Project;
import com.eric.mipro.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }
}
