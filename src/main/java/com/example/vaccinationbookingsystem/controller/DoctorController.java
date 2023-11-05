package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.dto.RequestDto.DoctorRequestDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.DoctorsNameResponseDto;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){

        try {
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getByGreaterThanGivenAge")
    public ResponseEntity getByGreaterThanGivenAge(@RequestParam("age") int age){

       DoctorsNameResponseDto doctorsNameResponseDto = doctorService.getByGreaterThanGivenAge(age);
       return new ResponseEntity(doctorsNameResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getDoctorWithHighestNoOfAppointments")
    public ResponseEntity getDoctorWithHighestNoOfAppointments(){

        String doctorName = doctorService.getDoctorWithHighestNoOfAppointments();
        return new ResponseEntity(doctorName, HttpStatus.OK);
    }


    @GetMapping("/getDoctorsListWithParticularCenter")
    public ResponseEntity getDoctorsListWithParticularCenter(@RequestParam("centerName") String centerName){

        try {
            List<Doctor> doctorList = doctorService.getDoctorsListWithParticularCenter(centerName);
            return new ResponseEntity(doctorList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
