package com.black9p.ppmtool.controller;

import com.black9p.ppmtool.domain.Project;
import com.black9p.ppmtool.domain.ProjectDTO;
import com.black9p.ppmtool.domain.ProjectResource;
import com.black9p.ppmtool.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 프로젝트 생성
     * @param projectDTO
     * @return
     */
    @PostMapping("")
    public ResponseEntity createNewProject(@Valid @RequestBody ProjectDTO projectDTO, BindingResult result) {
        // branch6 요거를 어떻게 빼야할지..
        if(result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            result.getFieldErrors().stream().forEach(e -> errorMap.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errorMap);
        }

        Project project = modelMapper.map(projectDTO, Project.class);
        Project savedProject = projectService.saveOrUpdateProject(project);

        URI uri = linkTo(ProjectController.class).slash(savedProject.getId()).toUri();

        ProjectResource projectResource = new ProjectResource(savedProject);
        projectResource.add(linkTo(ProjectController.class).withRel("projects"));

        return ResponseEntity.created(uri).body(projectResource);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity getProjectById(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId);

        return ResponseEntity.ok(project);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity deleteProjectById(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);

        return ResponseEntity.ok("Project: " + projectId + " is deleted.");
    }
}