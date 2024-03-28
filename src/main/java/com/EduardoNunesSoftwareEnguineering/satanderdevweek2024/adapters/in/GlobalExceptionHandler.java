package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.adapters.in;

import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.exception.ChampionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger =LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //setting the status code
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    //setting that this error will be used
    @ExceptionHandler(ChampionNotFoundException.class)
    public ResponseEntity<ApiError> handleDomainException(ChampionNotFoundException domainError){
        return ResponseEntity.unprocessableEntity().body(new ApiError(domainError.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleDomainException(Exception unexpectedError){
        String msg = "Oh no ! An unexpected error occurred";
        logger.error(msg,unexpectedError);
        return ResponseEntity.unprocessableEntity().body(new ApiError(msg));
    }
    public record ApiError(String message){}
}
