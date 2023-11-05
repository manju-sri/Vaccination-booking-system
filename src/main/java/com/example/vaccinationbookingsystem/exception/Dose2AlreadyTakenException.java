package com.example.vaccinationbookingsystem.exception;

public class Dose2AlreadyTakenException extends RuntimeException {

    public Dose2AlreadyTakenException(String message){
        super(message);
    }
}
