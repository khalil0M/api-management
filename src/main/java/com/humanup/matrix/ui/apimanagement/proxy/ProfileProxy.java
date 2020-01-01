package com.humanup.matrix.ui.apimanagement.proxy;

import com.humanup.matrix.ui.apimanagement.dto.ProfileDTO;
import com.humanup.matrix.ui.apimanagement.dto.TypeSkillsDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "collaborator-app-v1")
@CacheConfig(cacheNames = {"api-management"})

public interface ProfileProxy {

    @Cacheable(value = "profile-by-title", key = "#title")
    @RequestMapping(value="/profile", method= RequestMethod.GET)
    ProfileDTO findProfileByTitle(@RequestParam(value = "title", defaultValue = "Spring Developer") String title);
}
