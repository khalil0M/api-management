package com.humanup.matrix.ui.apimanagement.dto;

import java.util.List;

public class PersonDTO {
    private String firstName;
    private String lastName;
    private String mailAdresses;
    private String birthDate;
    private String profile;
    private List<SkillDTO> skillVOList;


    public PersonDTO() {
    }

    public PersonDTO(String firstName, String lastName, String mailAdresses, String birthDate, String profile, List<SkillDTO> skillVOList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailAdresses = mailAdresses;
        this.birthDate = birthDate;
        this.profile = profile;
        this.skillVOList = skillVOList;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMailAdresses() {
        return mailAdresses;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getProfile() {
        return profile;
    }

    public List<SkillDTO> getSkillVOList() {
        return skillVOList;
    }



    public static class Builder {
        private String firstName;
        private String lastName;
        private String mailAdresses;
        private String birthDate;
        private String profile;
        private List<SkillDTO> skillVOList;


        public Builder() {
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setMailAdresses(String mailAdresses) {
            this.mailAdresses = mailAdresses;
            return this;
        }

        public Builder setBirthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setProfile(String profile) {
            this.profile = profile;
            return this;
        }

        public Builder setSkills(List<SkillDTO> skillVOList) {
            this.skillVOList = skillVOList;
            return this;
        }



        public PersonDTO build() {
            return new PersonDTO(firstName, lastName, mailAdresses, birthDate, profile,skillVOList);
        }
    }
}

