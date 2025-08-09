package com.springboot.authentica.controller;

import com.springboot.authentica.io.ProfileRequest;
import com.springboot.authentica.io.ProfileResponse;
import com.springboot.authentica.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody ProfileRequest request){
        ProfileResponse response = profileService.createProfile(request);
        return response;
    }
}
