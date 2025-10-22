package rs.ac.bg.etf.dipl.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import rs.ac.bg.etf.dipl.dto.error.SubErrorDto;

@Getter
public abstract class CustomException extends RuntimeException {

    private HttpStatus httpStatus;
    private List<SubErrorDto> subErrors = new ArrayList<>();

    public CustomException(String errorDescription, HttpStatus httpStatus) {
        super(errorDescription);
        this.httpStatus = httpStatus;
    }

    public CustomException(String errorDescription, HttpStatus httpStatus, List<SubErrorDto> subErrors) {
        super(errorDescription);
        this.httpStatus = httpStatus;
        this.subErrors = subErrors;
    }
}
