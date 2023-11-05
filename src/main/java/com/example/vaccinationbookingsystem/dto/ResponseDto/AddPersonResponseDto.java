package com.example.vaccinationbookingsystem.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddPersonResponseDto {

    String name;

    String message;
}
