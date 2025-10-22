package rs.ac.bg.etf.dipl.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import rs.ac.bg.etf.dipl.dto.error.SubErrorDto;

public class ResourceNotFoundException extends CustomException {

    public ResourceNotFoundException(String errorDescription) {
        super(errorDescription, HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException(SubErrorDto... subErrors) {
        super("Resource not found", HttpStatus.NOT_FOUND, Arrays.asList(subErrors));
    }
}
