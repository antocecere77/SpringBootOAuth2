package com.antocecere77.ws.api.resourceserver.controllers;

import com.antocecere77.ws.api.resourceserver.response.UserRest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working...";
    }

    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    //@PreAuthorize("hasRole('developer')")
    //@Secured("ROLE_developer")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Delete user with id " + id + " and JWT subject " + jwt.getSubject();
    }

    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return UserRest.builder()
                .userId(id)
                .userFirstName("Antonio")
                .userLastName("Cecere")
                .build();
    }
}
