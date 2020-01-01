package com.humanup.matrix.ui.apimanagement.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanup.matrix.ui.apimanagement.dto.InterviewDTO;
import com.humanup.matrix.ui.apimanagement.dto.ProfileDTO;
import com.humanup.matrix.ui.apimanagement.proxy.CollaboratorManagementProxy;
import com.humanup.matrix.ui.apimanagement.proxy.PersonProxy;
import com.humanup.matrix.ui.apimanagement.dto.PersonDTO;
import com.humanup.matrix.ui.apimanagement.proxy.ProfileProxy;
import com.humanup.matrix.ui.apimanagement.vo.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonQuery implements GraphQLQueryResolver {
    final static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    PersonProxy personProxy;

    @Autowired
    ProfileProxy profileProxy;

    @Autowired
    CollaboratorManagementProxy collaboratorManagementProxy;

    public List<PersonVO> getListPerson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<PersonDTO> personListDTO=  mapper.readValue(personProxy.findAllPerson(),new TypeReference<List<PersonDTO>>(){});
        return personListDTO.stream()
                .map(p -> {
                    ProfileDTO profile = null;
                    try {
                        profile = mapper.readValue(profileProxy.findProfileByTitle(p.getProfile()), ProfileDTO.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return new PersonVO.Builder()
                    .setBirthDate(p.getBirthDate())
                    .setFirstName(p.getFirstName())
                    .setLastName(p.getLastName())
                    .setMailAdresses(p.getMailAdresses())
                    .setProfile(getBuildProfile(profile))
                    .setSkills(getCollectSkills(p))
                    .setInterviews(getCollectInterview(p.getMailAdresses()))
                   .build();
                }).collect(Collectors.toList());
    }



    public PersonVO getPersonByEmail(String email) {

        PersonDTO personDTO= null;
        ProfileDTO profile = null;
        try {
            personDTO = mapper.readValue(personProxy.findPersonByEmail(email), PersonDTO.class);
             profile = mapper.readValue(profileProxy.findProfileByTitle(personDTO.getProfile()),ProfileDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new PersonVO.Builder()
                            .setBirthDate(personDTO.getBirthDate())
                            .setFirstName(personDTO.getFirstName())
                            .setLastName(personDTO.getLastName())
                            .setMailAdresses(personDTO.getMailAdresses())
                            .setProfile(getBuildProfile(profile))
                            .setSkills(getCollectSkills(personDTO))
                            .build();
    }

    @NotNull
    private ProfileVO getBuildProfile(ProfileDTO profile) {
        return new ProfileVO.Builder()
                .setProfileTitle(profile.getProfileTitle())
                .setProfileDescription(profile.getProfileDescription())
                .setCountPerson(profile.getCountPerson())
                .build();
    }
    @NotNull
    private List<InterviewVO> getCollectInterview(String email) {
        List<InterviewDTO> interviewListDTO= null;
        try {
            interviewListDTO = mapper.readValue(collaboratorManagementProxy.findInteviewsByCollaboratuerEmail(email),new TypeReference<List<InterviewDTO>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return interviewListDTO.stream()
                .map(interview -> {
                    return new InterviewVO.Builder()
                            .setInterviewTitle(interview.getInterviewTitle())
                            .setInterviewDescription(interview.getInterviewDescription())
                            .setInterviewDate(interview.getInterviewDate())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @NotNull
    private List<SkillVO> getCollectSkills(PersonDTO p) {
        return p.getSkillVOList().stream()
                .map(skill -> {
                    return new SkillVO.Builder()
                            .setDescription(skill.getDescription())
                            .setLibelle(skill.getLibelle())
                            .setTypeSkills(new TypeSkillsVO.Builder()
                                    .setTitleSkill(null != skill.getTypeSkills() ? skill.getTypeSkills() : "")
                                    .build())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
