package com.eric.mipro.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor()
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String projectName;

    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updatedAt;

    private Project(@NotNull String projectName,
                    @NotNull String projectIdentifier,
                    @NotNull String description,
                    Date startDate,
                    Date endDate) {
        this.projectName = projectName;
        this.projectIdentifier = projectIdentifier;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static ProjectBuilder aProject() {
        return new ProjectBuilder();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }



@NoArgsConstructor()
@Data
public static class ProjectBuilder {

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "identifier is required")
    @Size(min = 4, max = 5, message = "Not allowed to use more than 4 and less than 5 char")
    private String projectIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ProjectBuilder projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }


    public ProjectBuilder projectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
        return this;
    }

    public ProjectBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public ProjectBuilder endDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Project build() {
        return new Project(projectName, projectIdentifier, description, startDate, endDate);
    }

}
}
