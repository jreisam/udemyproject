package jreis.development.udemyproject.services;

import jreis.development.udemyproject.domain.Project;
import jreis.development.udemyproject.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        // logic
        return projectRepository.save(project);
    }
}
