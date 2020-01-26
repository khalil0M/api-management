package com.humanup.matrix.ui.apimanagement.dto;

import java.util.List;

public class CollaboratorDTO {

    private String mailAdresse;
    private List<ProjectDTO> projectVOList;

    public CollaboratorDTO() {
    }

    public CollaboratorDTO(String mailAdresse, List<ProjectDTO> projectVOList) {
        this.mailAdresse = mailAdresse;
        this.projectVOList = projectVOList;

    }

    public String getMailAdresse() {
        return mailAdresse;
    }
    public List<ProjectDTO> getProjectVOList() {
        return projectVOList;
    }


    public static class Builder {
        private String mailAdresse;
        private List<ProjectDTO> projectVOList;

        public Builder() {
        }

        public Builder setMailAdresse(String mailAdresse) {
            this.mailAdresse = mailAdresse;
            return this;
        }

        public Builder setProjects(List<ProjectDTO> projectVOList) {
            this.projectVOList = projectVOList;
            return this;
        }

        public CollaboratorDTO build() {
            return new CollaboratorDTO(mailAdresse,projectVOList);
        }
    }

}
