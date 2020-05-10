package com.humanup.matrix.ui.apimanagement.proxy;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "training-app-v1", decode404 = true)
public interface ReviewProxy {
    @Cacheable(cacheNames = "reviews-by-email", key = "#email")
    @RequestMapping(value="/review/all/internemail", method= RequestMethod.GET)
    String findReviewsByEmail(@RequestParam(value = "internEmail") String email);

    @Cacheable(cacheNames = "review-all")
    @GetMapping(value="/review/all")
    String findAllReviews();
}
