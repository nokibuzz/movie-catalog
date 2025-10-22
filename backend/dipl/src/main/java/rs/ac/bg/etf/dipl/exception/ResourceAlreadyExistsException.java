package rs.ac.bg.etf.dipl.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import rs.ac.bg.etf.dipl.dto.error.SubErrorDto;

public class ResourceAlreadyExistsException extends CustomException {

    public ResourceAlreadyExistsException(String errorDescription) {
        super(errorDescription, HttpStatus.BAD_REQUEST);
    }

    public ResourceAlreadyExistsException(SubErrorDto... subErrors) {
        super("Resource already exists", HttpStatus.BAD_REQUEST, Arrays.asList(subErrors));
    }
}
