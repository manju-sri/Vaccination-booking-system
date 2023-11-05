package com.example.vaccinationbookingsystem.dto.RequestDto;

import com.example.vaccinationbookingsystem.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDose1RequestDto {

    int personId;

    DoseType doseType;
}
