package com.springboot.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "employees")
public class Employee implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "First Name  must not be empty.")
    @Size(min = 2, message = "First Name should have at least 2 characters.")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last Name  must not be empty.")
    @Size(min = 2, message = "Last Name should have at least 2 characters.")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Department must not be empty.")
    @Column(name = "department" , length = 20)
    private String department;

    @Email
    @NotEmpty(message = "Email must not be empty.")
    @Column(name = "email_address")
    private String emailId;

    @Column(name = "address" , length = 50)
    private String address;

    @NotNull
    @Size(min = 2, message = "Passport should have at least 2 characters.")
    private String passportNumber;





}