package com.humanup.matrix.ui.apimanagement.proxy;

import com.humanup.matrix.ui.apimanagement.dto.CollaboratorDTO;
import com.humanup.matrix.ui.apimanagement.dto.PersonDTO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "collaborator-management-app-v1")
public interface CollaboratorManagementProxy {
    @CachePut(cacheNames = "collaborator-by-email", key = "#collaborator.mailAdresses")
    @PostMapping(value="/collaborator")
    String saveCollaborator(@RequestBody CollaboratorDTO collaborator);

    @Cacheable(cacheNames ="collaborator-all")
    @GetMapping(value="/collaborator/all")
    String findCollaboratuers();

    @Cacheable(cacheNames ="collaborator-all-by-email", key = "#email")
    @GetMapping(value="/collaborator/all/interview")
    String findInteviewsByCollaboratuerEmail(@RequestParam(value = "email", defaultValue = "robot@sqli.com") String email);

    @Cacheable(cacheNames = "collaborator-by-email", key = "#email")
    @RequestMapping(value="/collaborator", method= RequestMethod.GET)
    String findCollaboratorByEmail(@RequestParam(value = "email", defaultValue = "robot@sqli.com") String email);
}
