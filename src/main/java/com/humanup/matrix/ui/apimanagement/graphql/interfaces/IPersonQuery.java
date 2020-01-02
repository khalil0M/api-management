package com.humanup.matrix.ui.apimanagement.graphql.interfaces;

import com.humanup.matrix.ui.apimanagement.vo.PersonVO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IPersonQuery {
    PersonVO getPersonByEmail(@NotNull final String email);

    List<PersonVO> getListPerson();
}
