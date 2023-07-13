package org.soneech.springcourse.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Not valid email")
    private String email;

    @NotEmpty(message = "Address shouldn't be empty")
    @Pattern(regexp = "([A-Z]\\w+, ){2}\\d{6}",
            message = "Address should be in this format: Country, City, Postal code (6 digits)")
    private String address;
}
