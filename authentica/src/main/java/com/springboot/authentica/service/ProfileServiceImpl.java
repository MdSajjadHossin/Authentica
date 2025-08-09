package com.springboot.authentica.service;

import com.springboot.authentica.entity.User;
import com.springboot.authentica.io.ProfileRequest;
import com.springboot.authentica.io.ProfileResponse;
import com.springboot.authentica.repository.UserRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Builder
public class ProfileServiceImpl implements ProfileService {

    private final UserRepo userRepo;


    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        User newProfile = convertToUserEntity(request);
        if(!userRepo.existsByEmail(request.getEmail())){
            newProfile = userRepo.save(newProfile);
            return convertToProfileResponse(newProfile);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Already exists...");


    }

    private ProfileResponse convertToProfileResponse(User newProfile){
        return ProfileResponse.builder()
                .name(newProfile.getName())
                .email(newProfile.getEmail())
                .userId(newProfile.getUserId())
                .isAccountVerified(newProfile.getIsAccountVerified())
                .build();

    }

    private User convertToUserEntity(ProfileRequest request){
        return User.builder()
                .email(request.getEmail())
                .userId(UUID.randomUUID().toString())
                .name(request.getName())
                .password(request.getPassword())
                .isAccountVerified(false)
                .resetOtpExpiredAt(0L)
                .verifyOTP(null)
                .verifyOtpExpiredAt(0L)
                .resetOTp(null)
                .build();
    }
}
