package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.RequestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.exception.PersonNotFoundException;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        // convert Dto into entity to save in Db
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setGender(addPersonRequestDto.getGender());
        person.setEmail(addPersonRequestDto.getEmail());

        // following 3 lines are optional, by default they were false and null
        person.setDose1Taken(false);
        person.setDose2Taken(false);
        person.setCertificate(null);

        Person savedPerson =  personRepository.save(person);

        //convert entity to Dto as response to client

        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("Congrats! You have been registered");

        return addPersonResponseDto;
    }


    public String updateEmailId(String oldEmailId, String newEmailId) {

      Person person = personRepository.findByEmail(oldEmailId);
      if(person==null){
          throw new PersonNotFoundException("Invalid EmailId");
      }

      person.setEmail(newEmailId);
      personRepository.save(person);
      return "Your email has been updated successfully";
    }



}
