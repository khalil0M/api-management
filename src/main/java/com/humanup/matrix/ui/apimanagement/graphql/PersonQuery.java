package com.humanup.matrix.ui.apimanagement.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanup.matrix.ui.apimanagement.dto.ProfileDTO;
import com.humanup.matrix.ui.apimanagement.proxy.PersonProxy;
import com.humanup.matrix.ui.apimanagement.dto.PersonDTO;
import com.humanup.matrix.ui.apimanagement.proxy.ProfileProxy;
import com.humanup.matrix.ui.apimanagement.vo.PersonVO;
import com.humanup.matrix.ui.apimanagement.vo.ProfileVO;
import com.humanup.matrix.ui.apimanagement.vo.SkillVO;
import com.humanup.matrix.ui.apimanagement.vo.TypeSkillsVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonQuery implements GraphQLQueryResolver {
    @Autowired
    PersonProxy personProxy;

    @Autowired
    ProfileProxy profileProxy;


    public List<PersonVO> getListPerson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<PersonDTO> personListDTO=  mapper.readValue(personProxy.findAllPerson(),new TypeReference<List<PersonDTO>>(){});
        return personListDTO.stream()
                .map(p -> {
                    ProfileDTO profile = profileProxy.findProfileByTitle(p.getProfile());
                    return new PersonVO.Builder()
                    .setBirthDate(p.getFirstName())
                    .setFirstName(p.getFirstName())
                    .setLastName(p.getLastName())
                    .setMailAdresses(p.getMailAdresses())
                    .setProfile(getBuildProfile(profile))
                    .setSkills(getCollectSkills(p))
                   .build();
                }).collect(Collectors.toList());
    }



    public PersonVO getPersonByEmail(String email) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PersonDTO personDTO=  mapper.readValue(personProxy.findPersonByEmail(email),PersonDTO.class);
        ProfileDTO profile = profileProxy.findProfileByTitle(personDTO.getProfile());
        return new PersonVO.Builder()
                            .setBirthDate(personDTO.getFirstName())
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
