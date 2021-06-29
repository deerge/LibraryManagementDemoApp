package com.github.deerge.librarymanager.api.security;

import com.github.deerge.librarymanager.dto.security.UserDto;
import com.github.deerge.librarymanager.exceptions.UserAlreadyRegisteredException;
import com.github.deerge.librarymanager.repository.security.UsersRepository;
import com.github.deerge.librarymanager.service.security.UsersService;
import io.micrometer.core.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UsersApi {
    private final UsersService usersService;
    private final UsersRepository usersRepository;

    public UsersApi(@NonNull UsersService usersService, @NonNull UsersRepository usersRepository) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }

    public Long singUpUser(UserDto userDto) {
        var maybeAlreadyRegistered = usersRepository.findByEmail(userDto.getEmail());
        if (maybeAlreadyRegistered.isPresent()) {
            throw new UserAlreadyRegisteredException(String.format("User with email %s already registered", userDto.getEmail()));
        }

        return usersService.singUpUser(userDto);
    }
}
