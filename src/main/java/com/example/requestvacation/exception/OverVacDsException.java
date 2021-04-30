package com.example.requestvacation.exception;

import lombok.Getter;

@Getter
public class OverVacDsException extends RuntimeException {

    private Double vacDs;

    public OverVacDsException(Double vacDs) {
        this.vacDs = vacDs;
    }

}
