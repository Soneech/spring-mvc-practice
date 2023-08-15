package org.soneech.springcourse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Not valid email")
    private String email;
}
