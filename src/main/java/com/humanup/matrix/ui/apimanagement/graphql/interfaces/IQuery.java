package com.humanup.matrix.ui.apimanagement.graphql.interfaces;

import com.humanup.matrix.ui.apimanagement.vo.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IQuery {
  PersonVO getPersonByEmail(@NotNull final String email);

  List<PersonVO> getListPerson();

  List<QuestionVO> getListQuestion();

  List<ChoiceVO> getListChoice();

  List<ChoiceVO> getChoicesByQuestionId(@NotNull final Long questionId);

  List<AnswerVO> getListAnswer();

  AnswerVO getAnswerByChoiceId(@NotNull final Long choiceId);

  List<CourseVO> getListCourses();

  List<TrainerVO> getListTrainers();

  List<ReviewVO> getListReviews();

  List<CourseTypeVO> getListCourseTypes();
}
