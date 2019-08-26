package com.digis.vaa25.demo.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
public class User {

    /**
     * Usually @Id has type of Long. But we have only 4 fields in condition.
     */
    @Id
    private String login;
    /**
     * Name, surname.
     */
    private String fullName;
    /**
     * Date of birthday.
     */
    private LocalDate dob;
    /**
     * MALE, FEMALE.
     */
    private Gender gender;

}
