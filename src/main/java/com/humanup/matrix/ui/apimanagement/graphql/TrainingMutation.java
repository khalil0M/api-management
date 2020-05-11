package com.humanup.matrix.ui.apimanagement.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.humanup.matrix.ui.apimanagement.dto.*;
import com.humanup.matrix.ui.apimanagement.graphql.builder.ObjectBuilder;
import com.humanup.matrix.ui.apimanagement.graphql.interfaces.ITrainingMutation;
import com.humanup.matrix.ui.apimanagement.proxy.*;
import com.humanup.matrix.ui.apimanagement.vo.CourseTypeVO;
import com.humanup.matrix.ui.apimanagement.vo.CourseVO;
import com.humanup.matrix.ui.apimanagement.vo.ReviewVO;
import com.humanup.matrix.ui.apimanagement.vo.TrainerVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class TrainingMutation implements GraphQLMutationResolver, ITrainingMutation {

  private static final DateTimeFormatter inputFormatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
  private static final DateTimeFormatter outputFormatter =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

  @Autowired CourseTypeProxy courseTypeProxy;
  @Autowired TrainerProxy trainerProxy;
  @Autowired CourseProxy courseProxy;
  @Autowired InternProxy internProxy;
  @Autowired ReviewProxy reviewProxy;

  @Override
  public CourseTypeVO createCourseType(@NotNull String typeTitle) throws JsonProcessingException {
    final CourseTypeDTO courseTypeDto = CourseTypeDTO.builder().typeTitle(typeTitle).build();
    final CourseTypeDTO saveCourseType =
        ObjectBuilder.mapper.readValue(
            courseTypeProxy.saveCourseType(courseTypeDto), CourseTypeDTO.class);
    return Optional.ofNullable(saveCourseType)
        .map(courseType -> CourseTypeVO.builder().typeTitle(courseType.getTypeTitle()).build())
        .orElse(null);
  }

  @Override
  public TrainerVO createTrainer(
      @NotNull String name, @NotNull String address, @NotNull String phone, @NotNull String email)
      throws JsonProcessingException {
    final TrainerDTO trainerDto =
        TrainerDTO.builder().name(name).address(address).phone(phone).email(email).build();
    final TrainerDTO saveTrainer =
        ObjectBuilder.mapper.readValue(trainerProxy.saveTrainer(trainerDto), TrainerDTO.class);
    return Optional.ofNullable(saveTrainer)
        .map(
            trainer ->
                TrainerVO.builder()
                    .name(trainer.getName())
                    .address(trainer.getAddress())
                    .phone(trainer.getPhone())
                    .email(trainer.getEmail())
                    .build())
        .orElse(null);
  }

  @Override
  public CourseVO createCourse(
      @NotNull String typeTitle,
      @NotNull String trainerEmail,
      @NotNull String title,
      @NotNull String description,
      @NotNull String startDate,
      @NotNull String endDate)
      throws JsonProcessingException {
    final CourseDTO courseDto =
        CourseDTO.builder()
            .courseTypeTitle(typeTitle)
            .trainerEmail(trainerEmail)
            .title(title)
            .description(description)
            .startDate(
                Optional.ofNullable(startDate)
                    .map(date -> LocalDateTime.parse(date, inputFormatter))
                    .orElse(null))
            .endDate(
                Optional.ofNullable(endDate)
                    .map(date -> LocalDateTime.parse(date, inputFormatter))
                    .orElse(null))
            .build();
    final CourseDTO saveCourse =
        ObjectBuilder.mapper.readValue(courseProxy.saveCourse(courseDto), CourseDTO.class);
    return Optional.ofNullable(saveCourse)
        .map(
            course ->
                CourseVO.builder()
                    .courseTypeTitle(course.getCourseTypeTitle())
                    .trainerEmail(course.getTrainerEmail())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .startDate(
                        Optional.ofNullable(course.getStartDate())
                            .map(date -> date.format(outputFormatter))
                            .orElse(null))
                    .endDate(
                        Optional.ofNullable(course.getEndDate())
                            .map(date -> date.format(outputFormatter))
                            .orElse(null))
                    .build())
        .orElse(null);
  }

  @Override
  public ReviewVO createReview(
      @NotNull String internId,
      @NotNull String courseId,
      @NotNull String createdOn,
      @NotNull int score)
      throws JsonProcessingException {
    final CourseDTO course =
        ObjectBuilder.mapper.readValue(courseProxy.findCourseById(courseId), CourseDTO.class);
    if (null == course) {
      return null;
    }
    final InternDTO intern =
        ObjectBuilder.mapper.readValue(internProxy.findInternById(internId), InternDTO.class);
    if (null == intern) {
      return null;
    }
    final ReviewDTO reviewDto =
        ReviewDTO.builder()
            .courseTitle(course.getTitle())
            .internEmail(intern.getEmailPerson())
            .createdOn(
                Optional.ofNullable(createdOn)
                    .map(date -> LocalDateTime.parse(date, inputFormatter))
                    .orElse(null))
            .score(score)
            .build();
    final ReviewDTO saveReview =
        ObjectBuilder.mapper.readValue(reviewProxy.saveReview(reviewDto), ReviewDTO.class);
    return Optional.ofNullable(saveReview)
        .map(
            review ->
                ReviewVO.builder()
                    .courseTitle(review.getCourseTitle())
                    .internEmail(review.getInternEmail())
                    .createdOn(
                        Optional.ofNullable(review.getCreatedOn())
                            .map(date -> date.format(outputFormatter))
                            .orElse(null))
                    .score(review.getScore())
                    .build())
        .orElse(null);
  }
}
