package com.example.vaccinationbookingsystem.dto.ResponseDto;

import com.example.vaccinationbookingsystem.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VaccinationCenterResponseDto {

    String centerName;

    CenterType centerType;

    String centerAddress;
}
