package com.humanup.matrix.ui.apimanagement.proxy;

import com.humanup.matrix.ui.apimanagement.dto.ChoiceDTO;
import com.humanup.matrix.ui.apimanagement.dto.QuestionDTO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "qcm-app-v1")
public interface ChoiceProxy {

    @CachePut(cacheNames = "choice_by_choiceId", key = "#choice.choiceId")
    @PostMapping(value="/choice")
    String saveChoice(@RequestBody ChoiceDTO choice);

    @Cacheable(cacheNames ="choice-all")
    @GetMapping(value="/choice/all")
    String findAllChoice();

}
