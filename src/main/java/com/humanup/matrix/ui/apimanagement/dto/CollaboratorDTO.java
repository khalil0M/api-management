package com.humanup.matrix.ui.apimanagement.dto;

public class CollaboratorDTO {

    private String mailAdresse;

    public CollaboratorDTO() {
    }

    public CollaboratorDTO(String mailAdresse) {
        this.mailAdresse = mailAdresse;
    }

    public String getMailAdresse() {
        return mailAdresse;
    }

    public static class Builder {
        private String mailAdresse;

        public Builder() {
        }

        public Builder setMailAdresse(String mailAdresse) {
            this.mailAdresse = mailAdresse;
            return this;
        }

        public CollaboratorDTO build() {
            return new CollaboratorDTO(mailAdresse);
        }
    }

}
