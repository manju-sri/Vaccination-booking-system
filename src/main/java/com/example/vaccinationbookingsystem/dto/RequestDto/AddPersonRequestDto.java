package com.example.vaccinationbookingsystem.dto.RequestDto;

import com.example.vaccinationbookingsystem.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.FetchProfile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddPersonRequestDto {

    String name;

    int age;

    String email;

    Gender gender;

}
