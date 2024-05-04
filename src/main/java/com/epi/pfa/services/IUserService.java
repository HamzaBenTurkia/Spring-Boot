package com.epi.pfa.services;

import com.epi.pfa.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


public interface IUserService {
    User saveUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    Optional<User> findById(long id);
}
