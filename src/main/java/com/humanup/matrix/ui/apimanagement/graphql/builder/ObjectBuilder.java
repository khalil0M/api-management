package com.humanup.matrix.ui.apimanagement.graphql.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanup.matrix.ui.apimanagement.dto.*;
import com.humanup.matrix.ui.apimanagement.proxy.CollaboratorManagementProxy;
import com.humanup.matrix.ui.apimanagement.proxy.CourseProxy;
import com.humanup.matrix.ui.apimanagement.proxy.ReviewProxy;
import com.humanup.matrix.ui.apimanagement.vo.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectBuilder {
  private static final Logger LOGGER = LoggerFactory.getLogger(ObjectBuilder.class);
  public static final ObjectMapper mapper = new ObjectMapper();

  @NotNull
  public static PersonVO buildPerson(@NotNull final PersonDTO person) {
    return PersonVO.builder()
        .mailAdresses(person.getMailAdresses())
        .firstName(person.getFirstName())
        .lastName(person.getLastName())
        .birthDate(person.getBirthDate())
        .build();
  }

  @NotNull
  public static ChoiceVO buildChoice(@NotNull final ChoiceDTO choice) {
    return ChoiceVO.builder()
        .choiceText(choice.getChoiceText())
        .percentage(choice.getPercentage())
        .build();
  }

  @NotNull
  public static QuestionVO buildQuestion(@NotNull final QuestionDTO question) {
    return QuestionVO.builder()
        .questionId(question.getQuestionId())
        .questionText(question.getQuestionText())
        .build();
  }

  @NotNull
  public static List<CourseVO> buildCollectionCourseByEmailPerson(
      @NotNull final String email, @NotNull final CourseProxy courseProxy) {
    List<CourseDTO> coursesDTO = null;
    try {
      coursesDTO =
          mapper.readValue(
              courseProxy.findCoursesByEmail(email), new TypeReference<List<CourseDTO>>() {});
    } catch (final Exception e) {
      LOGGER.error("Exception Parsing List<CourseVO> {}", email, e);
      return Collections.emptyList();
    }
    return coursesDTO.stream()
        .map(
            course -> {
              return CourseVO.builder()
                  .courseTypeTitle(course.getCourseTypeTitle())
                  .description(course.getDescription())
                  .title(course.getTitle())
                  .startDate(course.getStartDate())
                  .endDate(course.getEndDate())
                  .build();
            })
        .collect(Collectors.toList());
  }

  @NotNull
  public static List<ReviewVO> buildCollectionReviewByEmailPerson(
      @NotNull final String email, @NotNull final ReviewProxy reviewProxy) {
    List<ReviewDTO> reviewsDTO = null;
    try {
      reviewsDTO =
          mapper.readValue(
              reviewProxy.findReviewsByEmail(email), new TypeReference<List<ReviewDTO>>() {});
    } catch (final Exception e) {
      LOGGER.error("Exception Parsing List<ReviewVO> {}", email, e);
      return Collections.emptyList();
    }
    return reviewsDTO.stream()
        .map(
            review -> {
              return ReviewVO.builder()
                  .courseTitle(review.getCourseTitle())
                  .internEmail(review.getInternEmail())
                  .createdOn(review.getCreatedOn())
                  .score(review.getScore())
                  .build();
            })
        .collect(Collectors.toList());
  }

  @NotNull
  public static ProfileVO buildProfile(@NotNull final ProfileDTO profile) {
    return new ProfileVO.Builder()
        .setProfileTitle(profile.getProfileTitle())
        .setProfileDescription(profile.getProfileDescription())
        .setCountPerson(profile.getCountPerson())
        .build();
  }

  @NotNull
  public static List<InterviewVO> buildCollectionInterviewByEmailPerson(
      @NotNull final String email,
      @NotNull final CollaboratorManagementProxy collaboratorManagementProxy) {
    List<InterviewDTO> interviewListDTO = null;
    try {
      interviewListDTO =
          mapper.readValue(
              collaboratorManagementProxy.findInteviewsByCollaboratuerEmail(email),
              new TypeReference<List<InterviewDTO>>() {});
    } catch (JsonProcessingException e) {
      LOGGER.error("Exception Parsing   List<InterviewVO>  {}", email, e);
      return Collections.emptyList();
    }

    return interviewListDTO.stream()
        .map(
            interview -> {
              return new InterviewVO.Builder()
                  .setInterviewTitle(interview.getInterviewTitle())
                  .setInterviewDescription(interview.getInterviewDescription())
                  .setInterviewDate(interview.getInterviewDate())
                  .build();
            })
        .collect(Collectors.toList());
  }

  @NotNull
  public static List<ProjectVO> buildCollectionProjectByEmailPerson(
      @NotNull final String email,
      @NotNull final CollaboratorManagementProxy collaboratorManagementProxy) {
    List<ProjectDTO> projectListDTO = null;
    try {
      projectListDTO =
          mapper.readValue(
              collaboratorManagementProxy.findProjectsCollaboratorByEmail(email),
              new TypeReference<List<ProjectDTO>>() {});
    } catch (JsonProcessingException e) {
      LOGGER.error("Exception Parsing   List<ProjectVO>  {}", email, e);
      return Collections.emptyList();
    }

    return projectListDTO.stream()
        .map(
            project -> {
              return new ProjectVO.Builder()
                  .setProjectTitle(project.getProjectTitle())
                  .setProjectDescription(project.getProjectDescription())
                  .build();
            })
        .collect(Collectors.toList());
  }

  @NotNull
  public static List<ProjectVO> buildCollectionProjects(@NotNull final List<ProjectDTO> p) {
    return p.stream()
        .map(
            project -> {
              return new ProjectVO.Builder()
                  .setProjectTitle(project.getProjectTitle())
                  .setProjectDescription(project.getProjectDescription())
                  .build();
            })
        .collect(Collectors.toList());
  }

  @NotNull
  public static List<SkillVO> buildCollectionSkills(@NotNull final PersonDTO p) {
    return p.getSkillVOList().stream()
        .map(
            skill -> {
              return new SkillVO.Builder()
                  .setDescription(skill.getDescription())
                  .setLibelle(skill.getLibelle())
                  .setTypeSkills(
                      new TypeSkillsVO.Builder()
                          .setTitleSkill(null != skill.getTypeSkills() ? skill.getTypeSkills() : "")
                          .build())
                  .build();
            })
        .collect(Collectors.toList());
  }
}
