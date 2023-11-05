package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Enum.DoseType;
import com.example.vaccinationbookingsystem.dto.RequestDto.BookDose1RequestDto;
import com.example.vaccinationbookingsystem.dto.RequestDto.BookDose2RequestDto;
import com.example.vaccinationbookingsystem.model.Dose;


import com.example.vaccinationbookingsystem.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {
    @Autowired
    DoseService doseService;

    @PostMapping("/addDose1")
    public ResponseEntity addDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto){

        try {
            Dose doseTaken = doseService.saveDose1(bookDose1RequestDto);
            return new ResponseEntity(doseTaken, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addDose2")
    public ResponseEntity addDose2(@RequestBody BookDose2RequestDto bookDose2RequestDto){

        try {
            Dose doseTaken = doseService.saveDose2(bookDose2RequestDto);
            return new ResponseEntity(doseTaken, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
