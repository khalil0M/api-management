package com.humanup.matrix.ui.apimanagement.vo;

public class TypeEventsVO {
    private String titleEvent;

    public TypeEventsVO() {
    }

    public TypeEventsVO(String titleEvent) {
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

        public TypeEventsVO build() {
            return new TypeEventsVO(titleEvent);
        }
       
    }

}
