package com.recharging.booking_api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.recharging.booking_api.controllers.GlobalExceptionHandler;
import com.recharging.booking_api.entities.ErrorResponse;
import com.recharging.booking_api.exceptions.UserNotFoundException;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleResourceNotFoundException() {
        //SETUP
        UserNotFoundException ex = new UserNotFoundException(1);
        //EXECUTE
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleResourceNotFoundException(ex);
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testHandleEmptyResultDataAccessException() {
        //SETUP
        EmptyResultDataAccessException ex = new EmptyResultDataAccessException(1);
        //EXECUTE
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleEmptyResultDataAccessException(ex);
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testHandleValidationExceptions() {
        //SETUP
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        //MOCK
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());
        when(ex.getBindingResult()).thenReturn(bindingResult);
        //EXECUTE
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleValidationExceptions(ex);
        //ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testHandleException() {
        //SETUP
        Exception ex = new Exception();
        //EXECUTE
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleException(ex);
        //ASSERT
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}