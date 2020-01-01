package com.humanup.matrix.ui.apimanagement.proxy;

import com.humanup.matrix.ui.apimanagement.dto.PersonDTO;
import org.springframework.cache.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "collaborator-app-v1")
@CacheConfig(cacheNames = {"api-management"})
public interface PersonProxy {
    @CachePut(value = "person-by-email", key = "#person.mailAdresses")
    @PostMapping(value="/person")
    PersonDTO savePerson(@RequestBody PersonDTO person);

    @Cacheable(value ="person-all")
    @GetMapping(value="/person/all")
    List<PersonDTO> findAllPerson();

    @Cacheable(value = "person-by-email", key = "#email")
    @RequestMapping(value="/person", method= RequestMethod.GET)
    PersonDTO findPersonByEmail(@RequestParam(value="email", defaultValue="robot@sqli.com") String email);
}
