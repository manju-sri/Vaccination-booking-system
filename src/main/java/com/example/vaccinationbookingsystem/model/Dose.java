package com.example.vaccinationbookingsystem.model;


import com.example.vaccinationbookingsystem.Enum.DoseType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    String doseId;

    @Enumerated(value = EnumType.STRING)
    DoseType doseType;

    @CreationTimestamp
    Date  vaccinationDate;

    @ManyToOne
    @JoinColumn
    Person person;
}
