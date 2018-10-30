package jreis.development.udemyproject.services;

import jreis.development.udemyproject.domain.Project;
import jreis.development.udemyproject.exceptions.ProjectIdException;
import jreis.development.udemyproject.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Projeto ID: '" + project.getProjectIdentifier().toUpperCase() + "' já existe.");
        }
    }

    public Project findProjectByIdentifier(String projectIdent){
        Project project = projectRepository.findByProjectIdentifier(projectIdent.toUpperCase());
        if(project == null)
            throw new ProjectIdException("Projeto ID: '" + projectIdent.toUpperCase() + "' não existe.");

        return project;
    }
}
