package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.RequestDto.BookAppointmentRequestDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.BookAppointmentResponseDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.vaccinationbookingsystem.exception.DoctorNotFoundException;
import com.example.vaccinationbookingsystem.exception.PersonNotFoundException;
import com.example.vaccinationbookingsystem.model.Appointment;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.BookAppointmentRepository;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookAppointmentService {

    @Autowired
    BookAppointmentRepository bookAppointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    JavaMailSender javaMailSender;



    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) {

        Optional<Person> optionalPerson = personRepository.findById(bookAppointmentRequestDto.getPersonId());

        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Invalid Person Id");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());

        if (optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor Id");
        }

        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = bookAppointmentRepository.save(appointment);

        person.getPersonAppointments().add(appointment);
        doctor.getDoctorAppointments().add(appointment);

        Person savedPerson = personRepository.save(person);
        Doctor savedDoctor = doctorRepository.save(doctor);
        VaccinationCenter center = savedDoctor.getVaccinationCenter();

        String text = "Congrats!! "+savedPerson.getName()+" Your appointment has been booked with Doctor "+
                savedDoctor.getDoctorName() + ". Your vaccination center name is: " + center.getCenterName() + " Please reach at this address "+
                center.getCenterAddress() + " at this time: " + savedAppointment.getAppointmentDate()+" Thank You!!";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("manjumanohar07041998@gmail.com");
        simpleMailMessage.setTo(savedPerson.getEmail());
        simpleMailMessage.setSubject("Congrats!! Appointment Done");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        // prepare response Dto
        VaccinationCenterResponseDto vaccinationCenterResponseDto = new VaccinationCenterResponseDto();
        vaccinationCenterResponseDto.setCenterName(center.getCenterName());
        vaccinationCenterResponseDto.setCenterType(center.getCenterType());
        vaccinationCenterResponseDto.setCenterAddress(center.getCenterAddress());

        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setPersonName(savedPerson.getName());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getDoctorName());
        bookAppointmentResponseDto.setAppointmentId(savedAppointment.getAppointmentId());
        bookAppointmentResponseDto.setAppointmentDate(savedAppointment.getAppointmentDate());
        bookAppointmentResponseDto.setVaccinationCenterResponseDto(vaccinationCenterResponseDto);

        return bookAppointmentResponseDto;

    }
}
