package com.humanup.matrix.ui.apimanagement.dto;

import java.util.Date;

public class InterviewDTO {

    private String interviewTitle;
    private String interviewDescription;
    private Date interviewDate;
    private String collaborator;

    public InterviewDTO() {
    }

    public InterviewDTO(String interviewTitle, String interviewDescription, Date interviewDate, String collaborator) {
        this.interviewTitle = interviewTitle;
        this.interviewDescription = interviewDescription;
        this.interviewDate = interviewDate;
        this.collaborator = collaborator;
    }

    public String getInterviewTitle() {
        return interviewTitle;
    }
    public String getInterviewDescription() {
        return interviewDescription;
    }
    public Date getInterviewDate() {
        return interviewDate;
    }
    public String getCollaborator() {
        return collaborator;
    }

    public static class Builder {
        private String interviewTitle;
        private String interviewDescription;
        private Date interviewDate;
        private String collaborator;

        public Builder() {
        }

        public Builder setInterviewTitle(String interviewTitle) {
            this.interviewTitle = interviewTitle;
            return this;
        }
        public Builder setInterviewDescription(String interviewDescription) {
            this.interviewDescription = interviewDescription;
            return this;
        }
        public Builder setInterviewDate(Date interviewDate) {
            this.interviewDate = interviewDate;
            return this;
        }
        public Builder setCollaborator(String collaborator) {
            this.collaborator = collaborator;
            return this;
        }

        public InterviewDTO build() {
            return new InterviewDTO(interviewTitle,interviewDescription,interviewDate,collaborator);
        }

    }

}