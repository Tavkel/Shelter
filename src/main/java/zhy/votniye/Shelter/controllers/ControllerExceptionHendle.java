package zhy.votniye.Shelter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandle {

    Logger logger = LoggerFactory.getLogger(ControllerExceptionHandle.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        logger.warn(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PetException.class)
    public ResponseEntity<String> handlePetException(PetException ex) {

        logger.warn(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OwnerException.class)
    public ResponseEntity<String> handleOwnerException(OwnerException ex) {

        logger.warn(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ContactException.class)
    public ResponseEntity<String> handleContactException(ContactException ex) {

        logger.warn(ex.getMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AdoptionRequestEception.class)
    public ResponseEntity<String> handleAdoptionRequestEception(AdoptionRequestEception ex) {

        logger.warn(ex.getMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ReportException.class)
    public ResponseEntity<String> handleReportException(ReportException ex) {

        logger.warn(ex.getMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage);
    }

}

