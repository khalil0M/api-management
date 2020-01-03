package com.humanup.matrix.ui.apimanagement.vo;

public class ProjectVO {
    private String projectTitle;
    private String projectDescription;


    public ProjectVO() {
    }

    public ProjectVO(String projectTitle, String projectDescription) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }


    public static class Builder {
        private String projectTitle;
        private String projectDescription;

        public Builder() {
        }

        public ProjectVO.Builder setProjectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
            return this;
        }
        public ProjectVO.Builder setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
            return this;
        }

        public ProjectVO build() {
            return new ProjectVO(projectTitle,projectDescription);
        }

    }
}
