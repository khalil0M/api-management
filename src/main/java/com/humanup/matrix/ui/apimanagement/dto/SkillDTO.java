package com.humanup.matrix.ui.apimanagement.dto;

public class SkillDTO {

	private String libelle;
	private String description;
	private String typeSkills;
	
	protected SkillDTO() {
	}

	public SkillDTO(String libelle, String description, String typeSkills) {
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

	public String getTypeSkills() {
		return typeSkills;
	}

	public static class Builder {

		private String libelle;
		private String description;
		private String typeSkills;

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

		public Builder setTypeSkills(String typeSkills) {
			this.typeSkills = typeSkills;
			return this;
		}

		public SkillDTO build() {
			return new SkillDTO(libelle, description,typeSkills);
		}

	}
}
