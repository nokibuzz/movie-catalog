package rs.ac.bg.etf.dipl.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

    public BadRequestException(String errorDescription) {
        super(errorDescription, HttpStatus.BAD_REQUEST);
    }
}