package com.recharging.booking_api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.recharging.booking_api.entities.User;
import com.recharging.booking_api.exceptions.UserNotFoundException;
import com.recharging.booking_api.repositories.UserRepository;
import com.recharging.booking_api.security.SecurityConfiguration;
import com.recharging.booking_api.serviceImpls.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchUser_Valid() {
        //SETUP
        List<User> users = new ArrayList<>();
        users.add(new User());
        //MOCK
        when(userRepository.findByName(anyString())).thenReturn(users);
        //EXECUTE
        List<User> foundUsers = userServiceImpl.searchUser("John");
        //ASSERT
        assertEquals(1, foundUsers.size());
    }

    @Test
    public void testFindOneUser_Valid() {
        //SETUP
        User user = new User();
        //MOCK
        when(userRepository.findOneByName(anyString())).thenReturn(user);
        //EXECUTE
        User foundUser = userServiceImpl.findOneUser("John");
        //ASSERT
        assertNotNull(foundUser);
    }

    @Test
    public void testCreateUser_Valid() {
        //SETUP
        User user = new User();
        user.setPassword("password");
        //MOCK
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("null");
        when(userRepository.save(any(User.class))).thenReturn(user);
        //EXECUTE
        User createdUser = userServiceImpl.createUser(user);
        //ASSERT
        assertNotNull(createdUser);
        assertEquals("null", createdUser.getPassword());
    }

    @Test
    public void testGetUser_Valid() {
        //SETUP
        User user = new User();
        //MOCK
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        //EXECUTE
        User foundUser = userServiceImpl.getUser(1);
        //ASSERT
        assertNotNull(foundUser);
    }

    @Test
    public void testGetUser_NotFound() { //NO DATA IS NEEDED
        //MOCK
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        //EXECUTE & ASSERT
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUser(1));
    }

    @Test
    public void testGetAllUsers() {
        //SETUP
        List<User> users = new ArrayList<>();
        users.add(new User());
        //MOCK
        when(userRepository.findAll()).thenReturn(users);
        //EXECUTE
        List<User> allUsers = userServiceImpl.getAllUsers();
        //ASSERT
        assertEquals(1, allUsers.size());
    }

    @Test
    public void testUpdateUser_Valid() {
        //SETUP
        User user = new User();
        user.setName("John");
        //MOCK
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        //EXCEUTE
        User updatedUser = userServiceImpl.updateUser(1, user);
        //ASSERT
        assertEquals("John", updatedUser.getName());
    }

    @Test
    public void testDeleteUser_Valid() {
        // Setup
        User user = new User();
        // Mock
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(anyInt());
        //Execute
        userServiceImpl.deleteUser(1);
        //Verify
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).deleteById(1);
    }
}