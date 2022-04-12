package com.burak.ticketreservation.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AirlineValidator implements ConstraintValidator<Airline, String> {


    //String airline is the object to be validated
    @Override
    public boolean isValid(String airline, ConstraintValidatorContext context) {
        airline = airline.toLowerCase();
        if (airline.equals("thy") || airline.equals("pegasus") || airline.equals("lufthansa")) {
            return true; //means object is validated
        }
        return false;  //means object is not validated
    }
}
