package com.github.deerge.librarymanager.repository.security;

import com.github.deerge.librarymanager.model.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
