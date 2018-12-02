package com.black9p.ppmtool.domain;

import com.black9p.ppmtool.controller.ProjectController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ProjectResource extends Resource<Project> {

    public ProjectResource(Project content, Link... links) {
        super(content, links);
        add(linkTo(ProjectController.class).slash(content.getId()).withSelfRel());
    }
}
