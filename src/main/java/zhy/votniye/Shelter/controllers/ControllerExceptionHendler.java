package zhy.votniye.Shelter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHendler {

    Logger logger = LoggerFactory.getLogger(ControllerExceptionHendler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception ex) {

        logger.warn(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PetException.class)
    public ResponseEntity<String> handlerPetException(PetException ex) {

        logger.warn(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OwnerException.class)
    public ResponseEntity<String> handlerOwnerException(OwnerException ex) {

        logger.warn(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ContactException.class)
    public ResponseEntity<String> handlerContactException(ContactException ex) {

        logger.warn(ex.getMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AdoptionRequestEception.class)
    public ResponseEntity<String> handlerAdoptionRequestEception(AdoptionRequestEception ex) {

        logger.warn(ex.getMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ReportException.class)
    public ResponseEntity<String> handlerReportException(ReportException ex) {

        logger.warn(ex.getMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage);
    }

}

