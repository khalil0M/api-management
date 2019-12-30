package com.humanup.matrix.ui.apimanagement.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.humanup.matrix.ui.apimanagement.proxy.PersonProxy;
import com.humanup.matrix.ui.apimanagement.dto.PersonDTO;
import com.humanup.matrix.ui.apimanagement.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMutation implements GraphQLMutationResolver {
    @Autowired
    PersonProxy personProxy;


    public PersonVO createPerson(String firstName, String lastName, String mailAdresses, String birthDate, String profile){
        PersonDTO person = new PersonDTO.Builder()
                .setBirthDate(birthDate)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMailAdresses(mailAdresses)
                .setProfile(profile)
                .build();

        PersonDTO savePerson = personProxy.savePerson(person);
        return new PersonVO.Builder()
        .setMailAdresses(savePerson.getMailAdresses())
        .build();
    }
}
