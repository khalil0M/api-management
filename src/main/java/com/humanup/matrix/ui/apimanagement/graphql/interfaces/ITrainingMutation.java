package com.humanup.matrix.ui.apimanagement.graphql.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.humanup.matrix.ui.apimanagement.vo.CourseTypeVO;
import com.humanup.matrix.ui.apimanagement.vo.CourseVO;
import com.humanup.matrix.ui.apimanagement.vo.ReviewVO;
import com.humanup.matrix.ui.apimanagement.vo.TrainerVO;
import org.jetbrains.annotations.NotNull;

public interface ITrainingMutation {

  CourseTypeVO createCourseType(@NotNull String typeTitle) throws JsonProcessingException;

  TrainerVO createTrainer(
      @NotNull String name, @NotNull String address, @NotNull String phone, @NotNull String email)
      throws JsonProcessingException;

  CourseVO createCourse(
      @NotNull String idType,
      @NotNull String idTrainer,
      @NotNull String title,
      @NotNull String description,
      @NotNull String startDate,
      @NotNull String endDate)
      throws JsonProcessingException;

  ReviewVO createReview(
      @NotNull String internId,
      @NotNull String courseId,
      @NotNull String createdOn,
      @NotNull int score)
      throws JsonProcessingException;
}
