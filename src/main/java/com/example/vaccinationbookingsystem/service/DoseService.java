package com.example.vaccinationbookingsystem.service;


import com.example.vaccinationbookingsystem.dto.RequestDto.BookDose1RequestDto;
import com.example.vaccinationbookingsystem.dto.RequestDto.BookDose2RequestDto;
import com.example.vaccinationbookingsystem.exception.Dose1AlreadyTakenException;
import com.example.vaccinationbookingsystem.exception.Dose2AlreadyTakenException;
import com.example.vaccinationbookingsystem.exception.PersonNotFoundException;
import com.example.vaccinationbookingsystem.model.Dose;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {


//    @Autowired
//    DoseRepository doseRepository;

    @Autowired
    PersonRepository personRepository;
//    public Dose saveDose1(int personId, DoseType doseType) {
//
//        Optional<Person> optionalPerson = personRepository.findById(personId);
//
//        if(!optionalPerson.isPresent()){  // To check whether the person exist or not
//            throw new PersonNotFoundException("Invalid Person Id");
//        }
//
//        Person person1 = optionalPerson.get();
//
//        if(person1.isDose1Taken()) throw new Dose1AlreadyTakenException("Dose 1 already Taken");
//
//        Dose dose = new Dose();
//        dose.setDoseType(doseType);
//        dose.setDoseId(String.valueOf(UUID.randomUUID()));
//        dose.setPerson(person1);
//
//        person1.setDose1Taken(true);
//        personRepository.save(person1);
//
//        return doseRepository.save(dose);
//    }

    public Dose saveDose1(BookDose1RequestDto bookDose1RequestDto) {

        Optional<Person> optionalPerson = personRepository.findById(bookDose1RequestDto.getPersonId());

        if(!optionalPerson.isPresent()){  // To check whether the person exist or not
            throw new PersonNotFoundException("Invalid Person Id");
        }

        Person person1 = optionalPerson.get();

        // To check where dose 1 is taken or not
        if(person1.isDose1Taken()) throw new Dose1AlreadyTakenException("Dose 1 already Taken");

        Dose dose = new Dose();
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setPerson(person1);

        person1.setDose1Taken(true);
        person1.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person1);

        return savedPerson.getDosesTaken().get(0);
    }

    public Dose saveDose2(BookDose2RequestDto bookDose2RequestDto) {

        Optional<Person> optionalPerson = personRepository.findById(bookDose2RequestDto.getPersonId());
        if(optionalPerson==null){
            throw new PersonNotFoundException("Invalid Person Id");
        }

        Person person = optionalPerson.get();
        if (person.isDose2Taken()){
            throw new Dose2AlreadyTakenException("Dose 2 Already Taken");
        }

        Dose dose = new Dose();
        dose.setDoseType(bookDose2RequestDto.getDoseType());
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setPerson(person);

        person.setDose2Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);

        return savedPerson.getDosesTaken().get(1);

    }
}
