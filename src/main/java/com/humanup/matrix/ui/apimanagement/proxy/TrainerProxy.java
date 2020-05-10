package com.humanup.matrix.ui.apimanagement.proxy;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "training-app-v1", decode404 = true)
public interface TrainerProxy {
    @Cacheable(cacheNames = "trainer-by-email", key = "#email")
    @RequestMapping(value="/trainer/email", method= RequestMethod.GET)
    String findTrainerByEmail(@RequestParam(value = "email") String email);

    @Cacheable(cacheNames = "trainer-all")
    @GetMapping(value="/trainer/all")
    String findAllTrainers();
}
