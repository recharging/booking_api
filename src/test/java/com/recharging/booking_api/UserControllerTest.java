package com.recharging.booking_api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.recharging.booking_api.controllers.UserController;
import com.recharging.booking_api.entities.User;
import com.recharging.booking_api.services.UserService;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchUser() {
        //SETUP
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        // MOCK
        when(userService.searchUser(anyString())).thenReturn(users);
        //EXECUTE
        ResponseEntity<ArrayList<User>> response = userController.searchUser("John");
        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testCreateUser() {
        //SETUP
        User user = new User();
        //MOCK
        when(userService.createUser(any(User.class))).thenReturn(user);
        //EXECUTE
        ResponseEntity<User> response = userController.createUser(user);
        //ASSERT
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        //SETUP
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        //MOCK
        when(userService.getAllUsers()).thenReturn(users);
        //EXECUTE
        ResponseEntity<ArrayList<User>> response = userController.getAllUsers();
        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetUser() {
        //SETUP
        User user = new User();
        //MOCK
        when(userService.getUser(anyInt())).thenReturn(user);
        //EXECUTE
        ResponseEntity<User> response = userController.getUser(1);
        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateUser() {
        //SETUP
        User user = new User();
        //MOCK
        when(userService.updateUser(anyInt(), any(User.class))).thenReturn(user);
        //EXECUTE
        ResponseEntity<User> response = userController.updateUser(1, user);
        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testDeleteUser() {
        //EXECUTE
        ResponseEntity<User> response = userController.deleteUser(1);
        //ASSERT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        //VERIFY
        verify(userService, times(1)).deleteUser(1);
    }
}