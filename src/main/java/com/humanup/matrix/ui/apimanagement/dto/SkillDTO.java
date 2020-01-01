package com.humanup.matrix.ui.apimanagement.dto;

public class SkillDTO {

	private Long  idTypeSkills;
	private String libelle;
	private String description;
	private String typeSkills;
	
	protected SkillDTO() {
	}

	public SkillDTO(Long idTypeSkills,String libelle, String description, String typeSkills) {
		this.idTypeSkills= idTypeSkills;
		this.libelle = libelle;
		this.description = description;
		this.typeSkills = typeSkills;
	}

	public Long getIdTypeSkills() {
		return idTypeSkills;
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
		private Long  idTypeSkills;
		private String libelle;
		private String description;
		private String typeSkills;

		public Builder() {
		}

		public Builder setIdTypeSkills(Long idTypeSkills) {
			this.idTypeSkills = idTypeSkills;
			return this;
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
			return new SkillDTO(idTypeSkills,libelle, description,typeSkills);
		}

	}
}
