package com.sample.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {

    private Long id;
    @Size(min =3, message="name should be At least three characters")
    @NotBlank
    private String name;
    @Email(message = "enter correct email address")
    private String emailId;
    @Size(min=10,max=10,message = "number should be 10 digits")
    private String mobile;


}
