package com.github.deerge.librarymanager.controller.security;

import com.github.deerge.librarymanager.api.security.UsersApi;
import com.github.deerge.librarymanager.dto.security.UserDto;
import io.micrometer.core.lang.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersApi usersApi;

    public UsersController(@NonNull UsersApi usersApi) {
        this.usersApi = usersApi;
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> singupUser(@RequestBody @Validated UserDto userDto) {
        var userId = usersApi.singUpUser(userDto);

        return ResponseEntity.ok(userId);
    }
}
