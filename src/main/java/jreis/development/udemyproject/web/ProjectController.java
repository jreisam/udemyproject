package jreis.development.udemyproject.web;

import jreis.development.udemyproject.domain.Project;
import jreis.development.udemyproject.services.MapValidationErrorsService;
import jreis.development.udemyproject.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorsService mapValidationErrorsService;

    @PostMapping("") // retornar para o front em JSON
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> erros = mapValidationErrorsService.MapValidation(result);
        if (erros != null)
            return erros;

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdent}")
    public ResponseEntity<?> getProjectByIdent(@PathVariable String projectIdent){
        Project project = projectService.findProjectByIdentifier(projectIdent);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
}
