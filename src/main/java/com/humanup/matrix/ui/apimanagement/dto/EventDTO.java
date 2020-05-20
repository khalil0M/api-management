package com.humanup.matrix.ui.apimanagement.dto;

public class EventDTO {

	private Long  idTypeEvents;
	private String libelle;
	private String description;
	private String typeEvents;

	protected EventDTO() {
	}

	public EventDTO(Long idTypeEvents, String libelle, String description, String typeEvents) {
		this.idTypeEvents= idTypeEvents;
		this.libelle = libelle;
		this.description = description;
		this.typeEvents = typeEvents;
	}

	public Long getIdTypeEvents() {
		return idTypeEvents;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getDescription() {
		return description;
	}

	public String getTypeEvents() {
		return typeEvents;
	}

	public static class Builder {
		private Long  idTypeEvents;
		private String libelle;
		private String description;
		private String typeEvents;

		public Builder() {
		}

		public Builder setIdTypeEvents(Long idTypeEvents) {
			this.idTypeEvents = idTypeEvents;
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

		public Builder setTypeEvents(String typeEvents) {
			this.typeEvents = typeEvents;
			return this;
		}

		public EventDTO build() {
			return new EventDTO(idTypeEvents,libelle, description,typeEvents);
		}

	}
}
