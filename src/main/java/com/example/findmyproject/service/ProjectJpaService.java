/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.*;
 *
 */

// Write your code here
package com.example.findmyproject.service;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.repository.ProjectJpaRepository;
import com.example.findmyproject.repository.ProjectRepository;
import com.example.findmyproject.repository.ResearcherJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectJpaService implements ProjectRepository {

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Autowired
    private ResearcherJpaRepository researcherJpaRepository;

    @Override
    public ArrayList<Project> getProjects() {
        List<Project> projectList = projectJpaRepository.findAll();
        ArrayList<Project> projects = new ArrayList<>(projectList);
        return projects;
    }

    @Override
    public Project getProjectById(int projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            return project;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Project addProject(Project project) {
        List<Integer> researcherIds = new ArrayList<>();
        for (Researcher researcher : project.getResearchers()) {
            researcherIds.add(researcher.getResearcherId());
        }

        List<Researcher> researchers = researcherJpaRepository.findAllById(researcherIds);

        if (researchers.size() != researcherIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        project.setResearchers(researchers);

        return projectJpaRepository.save(project);
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        try {
            Project newProject = projectJpaRepository.findById(projectId).get();
            if (project.getProjectName() != null) {
                newProject.setProjectName(project.getProjectName());
            }
            if (project.getBudget() != 0) {
                newProject.setBudget(project.getBudget());
            }
            if (project.getResearchers() != null) {
                List<Integer> researcherIds = new ArrayList<>();
                for (Researcher researcher : project.getResearchers()) {
                    researcherIds.add(researcher.getResearcherId());
                }
                List<Researcher> researchers = researcherJpaRepository.findAllById(researcherIds);
                if (researchers.size() != researcherIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                newProject.setResearchers(researchers);
            }
            return projectJpaRepository.save(newProject);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProject(int projectId) {
        try {
            projectJpaRepository.deleteById(projectId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Researcher> getProjectResearchers(int projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            return project.getResearchers();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
