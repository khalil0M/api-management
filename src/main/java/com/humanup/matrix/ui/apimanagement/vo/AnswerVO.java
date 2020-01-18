package com.humanup.matrix.ui.apimanagement.vo;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AnswerVO {

     ChoiceVO choice;
     PersonVO person;

}
