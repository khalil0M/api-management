package com.humanup.matrix.ui.apimanagement.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SkillVO {

	 String libelle;
	 String description;
	 TypeSkillsVO typeSkills;

}
