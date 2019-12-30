package com.humanup.matrix.ui.apimanagement.dto;

public class TypeSkillsDTO {
    private String titleSkill;
    
    public TypeSkillsDTO() {
    }

    public TypeSkillsDTO(String titleSkill) {
        this.titleSkill = titleSkill;
      
    }

    public String getTitleSkill() {
        return this.titleSkill;
    }
  
  
    public static class Builder{
        private String titleSkill;
        public Builder() {
        }

        public Builder setTitleSkill(String titleSkill) {
            this.titleSkill = titleSkill;
            return this;
        }

        public TypeSkillsDTO build() {
            return new TypeSkillsDTO(titleSkill);
        }
       
    }

}
