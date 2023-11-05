package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.dto.RequestDto.BookAppointmentRequestDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.BookAppointmentResponseDto;
import com.example.vaccinationbookingsystem.model.Appointment;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.service.BookAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class BookAppointmentController {

    @Autowired
    BookAppointmentService bookAppointmentService;

    @PostMapping("/bookAppointment")
    public ResponseEntity bookAppointment(
            @RequestBody BookAppointmentRequestDto bookAppointmentRequestDto){
      try {
          BookAppointmentResponseDto bookAppointmentResponseDto =
                  bookAppointmentService.bookAppointment(bookAppointmentRequestDto);
          return new ResponseEntity(bookAppointmentResponseDto, HttpStatus.CREATED);
      }
      catch (Exception e){
          return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
      }

    }


}
