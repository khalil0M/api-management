package com.humanup.matrix.ui.apimanagement.vo;

import com.humanup.matrix.ui.apimanagement.dto.ProfileDTO;
import com.humanup.matrix.ui.apimanagement.dto.SkillDTO;

import java.util.List;

public class PersonVO {
    private String firstName;
    private String lastName;
    private String mailAdresses;
    private String birthDate;
    private ProfileVO profile;
    private List<SkillVO> skillVOList;
    private List<InterviewVO> interviews;
    private List<ProjectVO> projects;

    public PersonVO() {
    }

    public PersonVO(String firstName, String lastName, String mailAdresses, String birthDate, ProfileVO profile, List<SkillVO> skillVOList,List<InterviewVO> interviews,List<ProjectVO> projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailAdresses = mailAdresses;
        this.birthDate = birthDate;
        this.profile = profile;
        this.skillVOList = skillVOList;
        this.interviews = interviews;
        this.projects = projects;
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

    public ProfileVO getProfile() {
        return profile;
    }

    public List<SkillVO> getSkillVOList() {
        return skillVOList;
    }

    public List<InterviewVO> getInterviews() {
        return interviews;
    }

    public List<ProjectVO> getProjectsList() {
        return projects;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String mailAdresses;
        private String birthDate;
        private ProfileVO profile;
        private List<SkillVO> skillVOList;
        private List<InterviewVO> interviews;
        private List<ProjectVO> projects;

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

        public Builder setProfile(ProfileVO profile) {
            this.profile = profile;
            return this;
        }

        public Builder setSkills(List<SkillVO> skillVOList) {
            this.skillVOList = skillVOList;
            return this;
        }

        public Builder setInterviews(List<InterviewVO> interviews) {
            this.interviews = interviews;
            return this;
        }

        public Builder setProjects(List<ProjectVO> projects) {
            this.projects = projects;
            return this;
        }

        public PersonVO build() {
            return new PersonVO(firstName, lastName, mailAdresses, birthDate, profile,skillVOList,interviews,projects);
        }
    }
}

