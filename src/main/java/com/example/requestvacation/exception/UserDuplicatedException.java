package com.example.requestvacation.exception;

import lombok.Getter;

@Getter
public class UserDuplicatedException extends RuntimeException {

    private String Id;

    public UserDuplicatedException(String Id) {
        this.Id = Id;
    }

}
