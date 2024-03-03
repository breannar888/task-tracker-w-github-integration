package com.tasktracker.tasktracker.controllers;

import com.tasktracker.tasktracker.models.Project;
import com.tasktracker.tasktracker.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getProjectById")
    public Project getProjectById(@RequestParam String id) {
        return projectService.getProjectByProjectId(id);
    }

    @GetMapping("/getProjectByName")
    public Project getProjectByName(@RequestParam String name) {
        return projectService.getProjectByName(name);
    }

    @GetMapping("/getAllProjectByUserId")
    public List<Project> getProjectByUserId(@RequestParam String userId) {
        return projectService.getProjectsByUserId(userId);
    }


}
