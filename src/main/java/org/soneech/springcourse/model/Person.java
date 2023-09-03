package org.soneech.springcourse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Not valid email")
    private String email;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // small m (mm) - for months

    private Date dateOfBirth;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.ORDINAL)  // by default
    private Mood mood;

    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    @Override
    public String toString() {
        return String.format(
                "Person{id=%d, name=%s, email=%s, dateOfBirth=%s, createdAt=%s}",
                id, name, email, dateOfBirth.toString(), createdAt.toString());
    }
}
