package com.mango.customer.controller;

import com.mango.customer.dto.UserDTO;
import com.mango.customer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping(produces = "application/json", path = "/user")
  public ResponseEntity<List<UserDTO>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @GetMapping(produces = "application/json", path = "/user/{email}")
  public ResponseEntity<UserDTO> getUser(@PathVariable String email) {
    return ResponseEntity.ok(userService.getUser(email));
  }

  @PostMapping(consumes = "application/json", produces = "application/json", path = "/signin")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
    return ResponseEntity.ok(userService.createUser(user));
  }

  @PutMapping(consumes = "application/json", produces = "application/json", path = "/updateUser/{email}")
  public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user, @PathVariable(name = "email") String email) {
    return ResponseEntity.ok(userService.updateUser(user, email));
  }
}
