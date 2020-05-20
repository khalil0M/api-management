package com.humanup.matrix.ui.apimanagement.dto;

public class TypeEventsDTO {
    private String titleEvent;

    public TypeEventsDTO() {
    }

    public TypeEventsDTO(String titleEvent) {
        this.titleEvent = titleEvent;
      
    }

    public String getTitleEvent() {
        return this.titleEvent;
    }
  
  
    public static class Builder{
        private String titleEvent;
        public Builder() {
        }

        public Builder setTitleEvent(String titleEvent) {
            this.titleEvent = titleEvent;
            return this;
        }

        public TypeEventsDTO build() {
            return new TypeEventsDTO(titleEvent);
        }
       
    }

}
