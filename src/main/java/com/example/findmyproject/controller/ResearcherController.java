/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.findmyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.findmyproject.service.ResearcherJpaService;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.model.Project;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResearcherController {
    @Autowired
    private ResearcherJpaService researcherJpaService;

    @GetMapping("/researchers")
    public ArrayList<Researcher> getResearchers() {
        return researcherJpaService.getResearchers();
    }

    @PostMapping("/researchers")
    public Researcher addResearcher(@RequestBody Researcher researcher) {
        return researcherJpaService.addResearcher(researcher);
    }

    @GetMapping("/researchers/{researcherId}")
    public Researcher getResearcherById(@PathVariable("researcherId") int researcherId) {
        return researcherJpaService.getResearcherById(researcherId);
    }

    @PutMapping("/researchers/{researcherId}")
    public Researcher updateResearcher(@PathVariable("researcherId") int researcherId,
            @RequestBody Researcher researcher) {
        return researcherJpaService.updateResearcher(researcherId, researcher);
    }

    @DeleteMapping("/researchers/{researcherId}")
    public void deleteResearcher(@PathVariable("researcherId") int researcherId) {
        researcherJpaService.deleteResearcher(researcherId);
    }

    @GetMapping("/researchers/{researcherId}/projects")
    public List<Project> getResearcherProjects(@PathVariable("researcherId") int researcherId) {
        return researcherJpaService.getResearcherProjects(researcherId);
    }
}