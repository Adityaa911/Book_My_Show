package org.example.bookmyshow.Controller;

import org.example.bookmyshow.Dtos.ResponseStatus;
import org.example.bookmyshow.Dtos.SignUpRequestDto;
import org.example.bookmyshow.Dtos.SignUpResponseDto;
import org.example.bookmyshow.Models.User;
import org.example.bookmyshow.Service.UserService;

public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService=userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto){
        SignUpResponseDto responseDto = new SignUpResponseDto();

        try {
                User user=  userService.signUp(responseDto.getUser().getName(),
                         responseDto.getUser().getEmail(),
                          responseDto.getUser().getPassword());

                responseDto.setUser(user);
                responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
          responseDto.setResponseStatus(ResponseStatus.FAILED);
        }
        return responseDto;
    }
}
