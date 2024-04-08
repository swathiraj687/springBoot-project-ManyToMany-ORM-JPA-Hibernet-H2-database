/*
 * You can use the following import statements
 *
 * import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 * 
 * import javax.persistence.*;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.findmyproject.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.example.findmyproject.model.Project;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "researcher")
public class Researcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int researcherId;
    @Column(name = "name")
    private String researcherName;
    @Column(name = "specialization")
    private String specialization;
    @ManyToMany(mappedBy = "researchers")
    @JsonIgnoreProperties("researchers")
    private List<Project> projects;

    public Researcher() {
    }

    public int getResearcherId() {
        return researcherId;
    }

    public void setResearcherId(int researcherId) {
        this.researcherId = researcherId;
    }

    public String getResearcherName() {
        return researcherName;
    }

    public void setResearcherName(String researcherName) {
        this.researcherName = researcherName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}