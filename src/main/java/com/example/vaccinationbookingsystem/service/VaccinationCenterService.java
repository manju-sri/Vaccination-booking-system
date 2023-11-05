package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.RequestDto.VaccinationCenterRequestDto;
import com.example.vaccinationbookingsystem.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    public VaccinationCenterResponseDto addCenter(VaccinationCenterRequestDto vaccinationCenterRequestDto) {

        //--> Convert Dto to entity
        VaccinationCenter center = new VaccinationCenter();
        center.setCenterName(vaccinationCenterRequestDto.getCenterName());
        center.setCenterType(vaccinationCenterRequestDto.getCenterType());
        center.setCenterAddress(vaccinationCenterRequestDto.getCenterAddress());

        // -->save entity to Db
        VaccinationCenter savedCenter = vaccinationCenterRepository.save(center);

        //--> Convert entity to Response Dto
        VaccinationCenterResponseDto vaccinationCenterResponseDto = new VaccinationCenterResponseDto();
        vaccinationCenterResponseDto.setCenterName(savedCenter.getCenterName());
        vaccinationCenterResponseDto.setCenterAddress(savedCenter.getCenterAddress());
        vaccinationCenterResponseDto.setCenterType(savedCenter.getCenterType());
        return vaccinationCenterResponseDto;
    }
}
