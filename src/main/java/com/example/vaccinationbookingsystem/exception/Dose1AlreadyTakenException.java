package com.example.vaccinationbookingsystem.exception;

public class Dose1AlreadyTakenException extends RuntimeException {

    public Dose1AlreadyTakenException(String message){
        super(message);
    }
}
