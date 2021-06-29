package com.github.deerge.librarymanager.service.security;

import com.github.deerge.librarymanager.dto.security.UserDto;
import com.github.deerge.librarymanager.model.security.User;
import com.github.deerge.librarymanager.repository.security.UsersRepository;
import io.micrometer.core.lang.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UsersService(@NonNull UsersRepository usersRepository,
                        @NonNull PasswordEncoder passwordEncoder,
                        @NonNull ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final var optionalUser = usersRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(String.format("User with email %s cannot be found.", email));
        }
    }

    public Long singUpUser(UserDto userDto) {
        final String encodePass = passwordEncoder.encode(userDto.getPassword());
        var user = modelMapper.map(userDto, User.class);
        user.setPassword(encodePass);
        user.setActive(true);
        user = usersRepository.save(user);

        return user.getId();
    }
}
