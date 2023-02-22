package org.anilcan.model.entity;

import lombok.*;
import org.anilcan.utility.enums.Gender;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Gender gender;
}
