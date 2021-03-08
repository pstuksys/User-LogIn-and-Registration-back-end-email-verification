package com.company.example.registration;

import com.company.example.appUser.AppUser;
import com.company.example.appUser.AppUserRole;
import com.company.example.appUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
       Boolean isValidEmail =  emailValidator.test(request.getEmail());

       if (!isValidEmail){
           throw new IllegalStateException("email not valid");
       }
        return appUserService.signUpUser(
                new AppUser(request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER)
        );
    }
}
