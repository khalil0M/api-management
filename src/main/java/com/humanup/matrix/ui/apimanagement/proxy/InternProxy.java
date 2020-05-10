package com.humanup.matrix.ui.apimanagement.proxy;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "training-app-v1", decode404 = true)
public interface InternProxy {
    @Cacheable(cacheNames = "intern-by-email", key = "#email")
    @RequestMapping(value="/intern/email", method= RequestMethod.GET)
    String findInternByEmail(@RequestParam(value = "email") String email);
}
