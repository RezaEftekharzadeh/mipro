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

    public Project saveOrUpdate(Project.ProjectBuilder project){

        return projectRepository.save(buidProject(project));
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

    public void deleteProjectById(Long id){
         projectRepository.deleteById(id);
    }

    public Project buidProject(Project.ProjectBuilder project){
        Project.ProjectBuilder projectBuilder = new Project.ProjectBuilder();
        return  projectBuilder.projectName(project.getProjectName())
                .projectIdentifier(project.getProjectIdentifier())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .build();
    }

    public Project.ProjectBuilder createPbfromProject(Optional<Project> projectApi){
        Project.ProjectBuilder projectBuilder = new Project.ProjectBuilder();
        if(projectApi.isPresent()) {
            Project project = projectApi.get();
            projectBuilder.setProjectName(project.getProjectName());
            projectBuilder.setProjectIdentifier(project.getProjectIdentifier());
            projectBuilder.setDescription(project.getDescription());
            projectBuilder.setStartDate(project.getStartDate());
            projectBuilder.setStartDate(project.getStartDate());
            projectBuilder.setEndDate(project.getEndDate());
            return projectBuilder;
        }
        return null;
    }


}
