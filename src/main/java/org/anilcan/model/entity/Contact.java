package org.anilcan.model.entity;

import lombok.*;
import org.anilcan.utility.enums.Gender;
import javax.persistence.*;

@Data
@Builder
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="firstName", nullable = false)
    private String firstName;
    @Column(name="lastName", nullable = false)
    private String lastName;
    @Column(name="phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name="gender", nullable = false)
    private Gender gender;
}
