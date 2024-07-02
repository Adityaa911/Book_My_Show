package org.example.bookmyshow.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshow.Models.User;

@Getter
@Setter
public class SignUpResponseDto {

    private User user;
    private ResponseStatus responseStatus;
}
