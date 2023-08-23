package org.example.controllers;

import org.example.models.UserProfile;
import org.example.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user/v1/")
public class UserController {

    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/{emailId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<UserProfile> getUser(
            @PathVariable("emailId") final String emailId
    ) {
        return ResponseEntity.ok(userService.getUser(emailId));
    }

    @RequestMapping(
            value = "add",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<UserProfile> addUser(
            @RequestBody UserProfile request) {
        return ResponseEntity.ok(userService.addUser(request));
    }
}
