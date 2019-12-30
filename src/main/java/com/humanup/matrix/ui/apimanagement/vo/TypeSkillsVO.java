package com.humanup.matrix.ui.apimanagement.vo;

public class TypeSkillsVO {
    private String titleSkill;
    
    public TypeSkillsVO() {
    }

    public TypeSkillsVO(String titleSkill) {
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

        public TypeSkillsVO build() {
            return new TypeSkillsVO(titleSkill);
        }
       
    }

}
