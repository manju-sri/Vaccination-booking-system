package com.example.vaccinationbookingsystem.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorsNameResponseDto {

    List<String> doctorsName = new ArrayList<>();
}
