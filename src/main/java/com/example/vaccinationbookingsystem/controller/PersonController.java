package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.dto.RequestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try {
            AddPersonResponseDto savedPersonResponseDto = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(savedPersonResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity("Invalid Data", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity updateEmaild(@RequestParam("oldEmail") String oldEmailId,
                                       @RequestParam("newEmail") String newEmailId){

        try {
            String response = personService.updateEmailId(oldEmailId, newEmailId);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    

}
