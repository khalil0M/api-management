package com.humanup.matrix.ui.apimanagement.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanup.matrix.ui.apimanagement.dto.InterviewDTO;
import com.humanup.matrix.ui.apimanagement.dto.ProfileDTO;
import com.humanup.matrix.ui.apimanagement.dto.ProjectDTO;
import com.humanup.matrix.ui.apimanagement.graphql.builder.ObjectBuilder;
import com.humanup.matrix.ui.apimanagement.graphql.interfaces.IPersonQuery;
import com.humanup.matrix.ui.apimanagement.proxy.CollaboratorManagementProxy;
import com.humanup.matrix.ui.apimanagement.proxy.PersonProxy;
import com.humanup.matrix.ui.apimanagement.dto.PersonDTO;
import com.humanup.matrix.ui.apimanagement.proxy.ProfileProxy;
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
                    List<ProjectDTO> projectsDTO = null;
                    try {
                        projectsDTO = ObjectBuilder.mapper.readValue(collaboratorManagementProxy.findCollaboratuers(), new TypeReference<List<ProjectDTO>>() {
                        });
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        LOGGER.error("Exception Parsing Projects {}", e);
                    }
                    try {
                        profile =  ObjectBuilder.mapper.readValue(profileProxy.findProfileByTitle(p.getProfile()), ProfileDTO.class);
                    } catch (JsonProcessingException e) {
                        LOGGER.error("Exception Parsing Profile {}", p.getProfile(), e);
                    }
                    return new PersonVO.Builder()
                            .setBirthDate(p.getBirthDate())
                            .setFirstName(p.getFirstName())
                            .setLastName(p.getLastName())
                            .setMailAdresses(p.getMailAdresses())
                            .setProfile(ObjectBuilder.buildProfile(profile))
                            .setSkills(ObjectBuilder.buildCollectionSkills(p))
                            .setInterviews(ObjectBuilder.buildCollectionInterview(p.getMailAdresses(),collaboratorManagementProxy))
                            .setProjects(ObjectBuilder.buildCollectionProjects(projectsDTO))
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
        return new PersonVO.Builder()
                .setBirthDate(personDTO.getBirthDate())
                .setFirstName(personDTO.getFirstName())
                .setLastName(personDTO.getLastName())
                .setMailAdresses(personDTO.getMailAdresses())
                .setProfile(ObjectBuilder.buildProfile(profileDTO))
                .setSkills(ObjectBuilder.buildCollectionSkills(personDTO))
                .setProjects(ObjectBuilder.buildCollectionProjects(projectsDTO))
                .build();
    }
}
