package com.miniproject.miniproject.data.Dto;



import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    private String id;

    @NotNull(message = "first name must have a value ")
    private String firstName;

    @NotNull(message = "lastname must have a value")
    private String lastName;

    @NotNull(message = "PhoneNumber cannot be blank")
    private String phoneNumber;

    private boolean isVerified;

}
