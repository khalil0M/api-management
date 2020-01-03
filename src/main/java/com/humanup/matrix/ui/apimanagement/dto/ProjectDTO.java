package com.humanup.matrix.ui.apimanagement.dto;

import java.util.Set;

public class ProjectDTO {

    private Long projectId;
    private String projectTitle;
    private String projectDescription;

    protected ProjectDTO() {}

    public ProjectDTO(String projectTitle, String projectDescription) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
    }

    public Long getProjectId() {
        return projectId;
    }
    public String getProjectTitle() {
        return projectTitle;
    }
    public String getProjectDescription() {
        return projectDescription;
    }

    public static class Builder{

        private String projectTitle;
        private String projectDescription;

        public Builder() {
        }

        public Builder setProjectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
            return this;
        }

        public Builder setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
            return this;
        }

        public ProjectDTO build(){
            return new ProjectDTO(projectTitle,  projectDescription);
        }

    }

}
