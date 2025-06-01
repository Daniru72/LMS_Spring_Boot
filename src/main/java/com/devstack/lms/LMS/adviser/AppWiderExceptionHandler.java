package com.devstack.lms.LMS.adviser;

import com.devstack.lms.LMS.exception.EntryNotFoundException;
import com.devstack.lms.LMS.util.StandardResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWiderExceptionHandler {
    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponseDto> handleEntryNotFoundException(EntryNotFoundException e) {
       return new ResponseEntity<>(
               new StandardResponseDto(
                       404, e.getMessage(), e
               ),
               HttpStatus.NOT_FOUND
       );
    }
}
