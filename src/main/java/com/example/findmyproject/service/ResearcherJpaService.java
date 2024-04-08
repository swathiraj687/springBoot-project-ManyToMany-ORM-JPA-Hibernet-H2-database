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

import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.model.Project;
import com.example.findmyproject.repository.ResearcherRepository;
import com.example.findmyproject.repository.ResearcherJpaRepository;
import com.example.findmyproject.repository.ProjectJpaRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResearcherJpaService implements ResearcherRepository {
    @Autowired
    private ResearcherJpaRepository researcherJpaRepository;

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Override
    public ArrayList<Researcher> getResearchers() {
        List<Researcher> researcherList = researcherJpaRepository.findAll();
        ArrayList<Researcher> researchers = new ArrayList<>(researcherList);
        return researchers;
    }

    @Override
    public Researcher addResearcher(Researcher researcher) {
        List<Integer> projectIds = new ArrayList<>();
        for (Project project : researcher.getProjects()) {
            projectIds.add(project.getProjectId());
        }
        List<Project> projects = projectJpaRepository.findAllById(projectIds);
        researcher.setProjects(projects);
        for (Project project : projects) {
            project.getResearchers().add(researcher);
        }
        Researcher savedResearcher = researcherJpaRepository.save(researcher);
        projectJpaRepository.saveAll(projects);
        return savedResearcher;
    }

    @Override
    public Researcher getResearcherById(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            return researcher;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Researcher updateResearcher(int researcherId, Researcher researcher) {
        try {
            Researcher newResearcher = researcherJpaRepository.findById(researcherId).get();
            if (researcher.getResearcherName() != null) {
                newResearcher.setResearcherName(researcher.getResearcherName());
            }
            if (researcher.getSpecialization() != null) {
                newResearcher.setSpecialization(researcher.getSpecialization());
            }
            if (researcher.getProjects() != null) {
                List<Project> projects = newResearcher.getProjects();
                for (Project project : projects) {
                    project.getResearchers().remove(newResearcher);
                }
                projectJpaRepository.saveAll(projects);
                List<Integer> newProjectIds = new ArrayList<>();
                for (Project project : researcher.getProjects()) {
                    newProjectIds.add(project.getProjectId());
                }
                List<Project> newProjects = projectJpaRepository.findAllById(newProjectIds);
                for (Project project : newProjects) {
                    project.getResearchers().add(newResearcher);
                }
                projectJpaRepository.saveAll(newProjects);
                newResearcher.setProjects(newProjects);
            }
            return researcherJpaRepository.save(newResearcher);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteResearcher(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            List<Project> projects = researcher.getProjects();
            for (Project project : projects) {
                project.getResearchers().remove(researcher);
            }
            projectJpaRepository.saveAll(projects);
            researcherJpaRepository.deleteById(researcherId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Project> getResearcherProjects(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            return researcher.getProjects();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}