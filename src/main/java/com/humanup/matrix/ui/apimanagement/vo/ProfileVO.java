package com.humanup.matrix.ui.apimanagement.vo;


public class ProfileVO {
    private String profileTitle;
    private String profileDescription;
    private int countPerson;

    public ProfileVO() {
    }

    public ProfileVO(String profileTitle, String profileDescription, int countPerson) {
        this.profileTitle = profileTitle;
        this.profileDescription = profileDescription;
        this.countPerson = countPerson;
    }

    public String getProfileTitle() {
        return this.profileTitle;
    }
    public String getProfileDescription() {
        return this.profileDescription;
    }

    public int getCountPerson() {
        return countPerson;
    }

    public static class Builder{
        private String profileTitle;
        private String profileDescription;
        private int countPerson;
        public Builder() {
        }

        public Builder setProfileTitle(String profileTitle) {
            this.profileTitle = profileTitle;
            return this;
        }
        public Builder setProfileDescription(String profileDescription) {
            this.profileDescription = profileDescription;
            return this;
        }

        public Builder setCountPerson(int countPerson) {
            this.countPerson = countPerson;
            return this;
        }

        public ProfileVO build() {
            return new ProfileVO(profileTitle, profileDescription,countPerson);
        }

    }

}
