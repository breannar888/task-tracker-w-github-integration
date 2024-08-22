package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.models.Project;
import com.tasktracker.tasktracker.repositories.ProjectRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class ProjectService {

    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    //get
    public Project getProjectByProjectId(String id) throws Exception {
        Optional<Project> project = projectRepo.findById(id);

        if(project.isPresent()) {
            return project.get();
        }

        System.out.println("Project not found for id: " + id);
        //TODO: throw custom ProductNotFoundException

    return null;
    }

    public Project getProjectByName(String name) throws Exception {
        Project project = projectRepo.findByName(name);

        if (project != null) {
            return project;
        }

        System.out.println("Project not found by name: " + name);
        return null;
    }

    public List<Project> getProjectsByUserId(String userId) throws Exception {

        List<Project> project = projectRepo.findByUserId(new ObjectId(userId));

        if (!project.isEmpty()) {
            return project;
        }

        System.out.println("No projects found for userId: " + userId);
        return null;
    }

    public Project createProject(Project project) throws Exception {
        return projectRepo.save(project);
    }

}
