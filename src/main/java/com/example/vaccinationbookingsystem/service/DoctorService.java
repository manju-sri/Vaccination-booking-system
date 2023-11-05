package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.RequestDto.DoctorRequestDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.DoctorsNameResponseDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.vaccinationbookingsystem.exception.CenterNotFoundException;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import com.example.vaccinationbookingsystem.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {

        Optional<VaccinationCenter> vaccinationCenter =
                vaccinationCenterRepository.findById(doctorRequestDto.getCenterId());

        if(vaccinationCenter.isEmpty()){
            throw new CenterNotFoundException("Invalid Center Id");
        }

        VaccinationCenter vaccinationCenter1 = vaccinationCenter.get();
         // convert dto to entity
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorRequestDto.getDoctorName());
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setEmail(doctorRequestDto.getEmail());
        doctor.setVaccinationCenter(vaccinationCenter1);

        //Add in vaccination_center's doctor list
        vaccinationCenter1.getDoctorList().add(doctor);

        // save both center and doctor(If we saved parent entity, it will reflect on child entity)
        VaccinationCenter savedCenter = vaccinationCenterRepository.save(vaccinationCenter1);

        List<Doctor> doctorsList = savedCenter.getDoctorList();
        Doctor savedDoctor = doctorsList.get(doctorsList.size()-1); // To get recently saved doctor's details

        VaccinationCenterResponseDto vaccinationCenterResponseDto = new VaccinationCenterResponseDto();
        vaccinationCenterResponseDto.setCenterName(savedCenter.getCenterName());
        vaccinationCenterResponseDto.setCenterType(savedCenter.getCenterType());
        vaccinationCenterResponseDto.setCenterAddress(savedCenter.getCenterAddress());

        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setName(savedDoctor.getDoctorName());
        doctorResponseDto.setMessage("Congrats! you have been registered successfully");
        doctorResponseDto.setVaccinationCenterResponseDto(vaccinationCenterResponseDto);

        return doctorResponseDto;
    }

    public DoctorsNameResponseDto getByGreaterThanGivenAge(int age) {

        List<Doctor> doctors = doctorRepository.getByAgeGreaterThan(age);
        List<String> doctorsName = new ArrayList<>();

        for(Doctor d: doctors){
          doctorsName.add(d.getDoctorName());
        }
        DoctorsNameResponseDto doctorsNameResponseDto = new DoctorsNameResponseDto();
        doctorsNameResponseDto.setDoctorsName(doctorsName);
        return doctorsNameResponseDto;
    }

    public String getDoctorWithHighestNoOfAppointments() {

        List<Doctor> doctorList = doctorRepository.findAll();
        String doctorName = null;
        int max=0;
        for (Doctor doc: doctorList){
            int totalAppointment =doc.getDoctorAppointments().size();
           if(totalAppointment>max){
               max = totalAppointment;
               doctorName = doc.getDoctorName();
           }
        }
        return doctorName;
    }

    public List<Doctor> getDoctorsListWithParticularCenter(String centerName) {

        VaccinationCenter vaccinationCenter = vaccinationCenterRepository.findByCenterName(centerName);
        if (vaccinationCenter==null){
            throw new CenterNotFoundException("Invalid Center Name");
        }

        return vaccinationCenter.getDoctorList();
    }
}
