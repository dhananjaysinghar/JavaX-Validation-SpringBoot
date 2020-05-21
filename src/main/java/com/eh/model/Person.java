package com.eh.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Person {


    @NotNull(message = "name should not be null")
    private String name;

   // @Pattern(message = "Email is not valid", regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    @NotNull(message = "email should not be null")
    @Email(message = "Email is not valid")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Mobile Number should be 10 digit")
    @NotNull(message = "mobile should not be null")
    private String mobile;


}
