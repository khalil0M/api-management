package com.humanup.matrix.ui.apimanagement.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanup.matrix.ui.apimanagement.dto.*;
import com.humanup.matrix.ui.apimanagement.graphql.builder.ObjectBuilder;
import com.humanup.matrix.ui.apimanagement.graphql.interfaces.IPersonQuery;
import com.humanup.matrix.ui.apimanagement.proxy.*;
import com.humanup.matrix.ui.apimanagement.vo.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonQuery implements GraphQLQueryResolver, IPersonQuery {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonQuery.class);


    @Autowired
    PersonProxy personProxy;

    @Autowired
    ProfileProxy profileProxy;

    @Autowired
    CollaboratorManagementProxy collaboratorManagementProxy;

    @Autowired
    TrainingProxy trainingProxy;

    @Autowired
    CourseProxy courseProxy;

    @Override
    public List<PersonVO> getListPerson(){
        List<PersonDTO> personListDTO = null;
        try {
            personListDTO = ObjectBuilder.mapper.readValue(personProxy.findAllPerson(), new TypeReference<List<PersonDTO>>() {
            });

        } catch (JsonProcessingException e) {
            LOGGER.error("Exception Parsing List<PersonVO> ", e);
        }
        return personListDTO.stream()
                .map(p -> {
                    ProfileDTO profile = null;
                    try {
                        profile =  ObjectBuilder.mapper.readValue(profileProxy.findProfileByTitle(p.getProfile()), ProfileDTO.class);
                    } catch (JsonProcessingException e) {
                        LOGGER.error("Exception Parsing Profile {}", p.getProfile(), e);
                    }
                    String mailAdresses = p.getMailAdresses();
                    return  PersonVO.builder()
                            .birthDate(p.getBirthDate())
                            .firstName(p.getFirstName())
                            .lastName(p.getLastName())
                            .mailAdresses(mailAdresses)
                            .profile(ObjectBuilder.buildProfile(profile))
                            .skillVOList(ObjectBuilder.buildCollectionSkills(p))
                            .trainer(ObjectBuilder.buildTrainer(mailAdresses,trainingProxy))
                            .interviews(ObjectBuilder.buildCollectionInterviewByEmailPerson(mailAdresses,collaboratorManagementProxy))
                            .projects(ObjectBuilder.buildCollectionProjectByEmailPerson(mailAdresses,collaboratorManagementProxy))
                            .courses(ObjectBuilder.buildCollectionCourseByEmailPerson(mailAdresses,courseProxy))
                            .build();
                }).collect(Collectors.toList());
    }


    @Override
    public PersonVO getPersonByEmail(@NotNull final String email) {
        PersonDTO personDTO = null;
        ProfileDTO profileDTO = null;
        List<ProjectDTO> projectsDTO = null;
        try {
            personDTO =  ObjectBuilder.mapper.readValue(personProxy.findPersonByEmail(email), PersonDTO.class);
            profileDTO =  ObjectBuilder.mapper.readValue(profileProxy.findProfileByTitle(personDTO.getProfile()), ProfileDTO.class);
            projectsDTO = ObjectBuilder.mapper.readValue(collaboratorManagementProxy.findProjectsCollaboratorByEmail(email), new TypeReference<List<ProjectDTO>>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception Parsing  Person {}", email, e);
        }
        return  PersonVO.builder()
                .birthDate(personDTO.getBirthDate())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .mailAdresses(personDTO.getMailAdresses())
                .profile(ObjectBuilder.buildProfile(profileDTO))
                .skillVOList(ObjectBuilder.buildCollectionSkills(personDTO))
                .projects(ObjectBuilder.buildCollectionProjects(projectsDTO))
                .build();
    }
}
