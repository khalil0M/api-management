package com.humanup.matrix.ui.apimanagement.graphql.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanup.matrix.ui.apimanagement.dto.InterviewDTO;
import com.humanup.matrix.ui.apimanagement.dto.PersonDTO;
import com.humanup.matrix.ui.apimanagement.dto.ProfileDTO;
import com.humanup.matrix.ui.apimanagement.proxy.CollaboratorManagementProxy;
import com.humanup.matrix.ui.apimanagement.vo.InterviewVO;
import com.humanup.matrix.ui.apimanagement.vo.ProfileVO;
import com.humanup.matrix.ui.apimanagement.vo.SkillVO;
import com.humanup.matrix.ui.apimanagement.vo.TypeSkillsVO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public  class ObjectBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectBuilder.class);
    public final static ObjectMapper mapper = new ObjectMapper();

    @NotNull
    public static ProfileVO buildProfile(@NotNull final ProfileDTO profile) {
        return new ProfileVO.Builder()
                .setProfileTitle(profile.getProfileTitle())
                .setProfileDescription(profile.getProfileDescription())
                .setCountPerson(profile.getCountPerson())
                .build();
    }

    @NotNull
    public static List<InterviewVO> buildCollectionInterview(@NotNull final String email, CollaboratorManagementProxy collaboratorManagementProxy) {
        List<InterviewDTO> interviewListDTO = null;
        try {
            interviewListDTO = mapper.readValue(collaboratorManagementProxy.findInteviewsByCollaboratuerEmail(email), new TypeReference<List<InterviewDTO>>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception Parsing   List<InterviewVO>  {}", email, e);
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
    public static List<SkillVO> buildCollectionSkills(@NotNull final PersonDTO p) {
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
