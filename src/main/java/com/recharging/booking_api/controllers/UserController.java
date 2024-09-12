package com.recharging.booking_api.controllers;

import java.util.ArrayList;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recharging.booking_api.entities.User;
import com.recharging.booking_api.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // SEARCH (GET ALL BY NAME)
    @GetMapping("/search")
    public ResponseEntity<ArrayList<User>> searchUser(@RequestParam String name) {
        ArrayList<User> foundUsers = userService.searchUser(name);
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    // FIND (GET ONE BY NAME)
    @GetMapping("/find")
    public ResponseEntity<User> findOneUser(@RequestParam String name) {
        User foundUser = userService.findOneUser(name);
        System.out.println(foundUser.getName());
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    // CREATE
    @PostMapping("")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        ArrayList<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Integer uid) {
        User foundUser = userService.getUser(uid);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "id") Integer uid, @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(uid, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id") Integer uid) {
        userService.deleteUser(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}