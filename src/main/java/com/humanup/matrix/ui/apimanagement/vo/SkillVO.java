package com.humanup.matrix.ui.apimanagement.vo;

public class SkillVO {

	private String libelle;
	private String description;
	private TypeSkillsVO typeSkills;

	protected SkillVO() {
	}

	public SkillVO(String libelle, String description, TypeSkillsVO typeSkills) {
		this.libelle = libelle;
		this.description = description;
		this.typeSkills = typeSkills;
	}


	public String getLibelle() {
		return libelle;
	}

	public String getDescription() {
		return description;
	}

	public TypeSkillsVO getTypeSkills() {
		return typeSkills;
	}

	public static class Builder {

		private String libelle;
		private String description;
		private TypeSkillsVO typeSkills;

		public Builder() {
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setLibelle(String libelle) {
			this.libelle = libelle;
			return this;
		}

		public Builder setTypeSkills(TypeSkillsVO typeSkills) {
			this.typeSkills = typeSkills;
			return this;
		}

		public SkillVO build() {
			return new SkillVO(libelle, description,typeSkills);
		}

	}
}
