package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.dto.RequestDto.VaccinationCenterRequestDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.vaccinationbookingsystem.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add")
    public VaccinationCenterResponseDto addVaccinationCenter(@RequestBody
                                               VaccinationCenterRequestDto vaccinationCenterRequestDto){

        VaccinationCenterResponseDto vaccinationCenterResponseDto =
                vaccinationCenterService.addCenter(vaccinationCenterRequestDto);
       // return new ResponseEntity(vaccinationCenterResponseDto, HttpStatus.CREATED);
        return vaccinationCenterResponseDto;
    }
}
