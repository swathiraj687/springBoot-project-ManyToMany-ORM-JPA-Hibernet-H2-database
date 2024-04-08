/*
 * You can use the following import statements
 *
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.findmyproject.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.model.Project;

public interface ResearcherRepository {
    ArrayList<Researcher> getResearchers();

    Researcher addResearcher(Researcher researcher);

    Researcher getResearcherById(int researcherId);

    Researcher updateResearcher(int researcherId, Researcher researcher);

    void deleteResearcher(int researcherId);

    List<Project> getResearcherProjects(int researcherId);
}