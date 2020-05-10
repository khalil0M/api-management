package com.humanup.matrix.ui.apimanagement.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
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
  Date startDate;
  Date endDate;
  List<ReviewVO> reviews;
}
