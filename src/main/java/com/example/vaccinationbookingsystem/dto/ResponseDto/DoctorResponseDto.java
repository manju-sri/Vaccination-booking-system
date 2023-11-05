package com.example.vaccinationbookingsystem.dto.ResponseDto;

import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorResponseDto {

    String name;

    String message;

    VaccinationCenterResponseDto vaccinationCenterResponseDto;
}
