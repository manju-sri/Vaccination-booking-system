package com.example.vaccinationbookingsystem.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookAppointmentRequestDto {

    int personId;

    int doctorId;
}
