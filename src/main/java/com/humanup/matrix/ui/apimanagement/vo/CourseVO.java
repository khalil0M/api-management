package com.humanup.matrix.ui.apimanagement.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseVO {
  String courseTypeTitle;
  String trainerEmail;
  String title;
  String description;
  String startDate;
  String endDate;
  List<ReviewVO> reviews;
}
