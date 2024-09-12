package com.recharging.booking_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recharging.booking_api.entities.User;
import com.recharging.booking_api.exceptions.UserNotFoundException;
import com.recharging.booking_api.repositories.UserRepository;
import com.recharging.booking_api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // New constructor to inject both UserRepository and PasswordEncoder
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public ArrayList<User> searchUser(String name) {
    List<User> foundUsers = userRepository.findByName(name);
    return (ArrayList<User>) foundUsers;
  }

  @Override
  public User findOneUser(String name) {
    User foundUser = userRepository.findOneByName(name);
    System.out.println("findOneUser " + name + " being called");
    return foundUser;
  }

  @Override
  public User createUser(User user) {
    // Encode the user's password
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User newUser = new User();
    newUser.setName(user.getName());
    newUser.setEmail(user.getEmail());
    newUser.setPassword(user.getPassword());
    // Save the new user
    return userRepository.save(newUser);
  }

  @Override
  public User getUser(Integer uid) {
    return userRepository.findById(uid).orElseThrow(() -> new UserNotFoundException(uid));
  }

  @Override
  public ArrayList<User> getAllUsers() {
    List<User> allUsers = userRepository.findAll();
    return (ArrayList<User>) allUsers;
  }

  @Override
  public User updateUser(Integer uid, User user) {
    // retrieve the customer from the database
    User userToUpdate = userRepository.findById(uid).orElseThrow(() -> new UserNotFoundException(uid));
    // update the customer retrieved from the database
    userToUpdate.setName(user.getName());
    userToUpdate.setEmail(user.getEmail());
    userToUpdate.setPassword(passwordEncoder.encode(user.getPassword())); // Ensure password is encoded
    // save the updated customer back to the database
    return userRepository.save(userToUpdate);
  }

  @Override
  public void deleteUser(Integer uid) {
    userRepository.findById(uid).orElseThrow(() -> new UserNotFoundException(uid));
    userRepository.deleteById(uid);
  }

}