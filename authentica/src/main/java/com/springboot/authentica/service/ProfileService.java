package com.springboot.authentica.service;

import com.springboot.authentica.io.ProfileRequest;
import com.springboot.authentica.io.ProfileResponse;

public interface ProfileService {

    ProfileResponse createProfile(ProfileRequest request);
}
