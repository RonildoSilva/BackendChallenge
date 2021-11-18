package com.example.user.endpoint.service;

import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Iterable<User> list(Pageable pageable) {
        log.info("Listing all users");
        return userRepository.findAll(pageable);
    }

    public User add(User user) {
        log.info("Added a new user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        log.info("Delete a user");
        userRepository.deleteById(id);
    }

    public void update(User user) {
        User tempUser = new User();
        tempUser.setName(user.getName());
        tempUser.setEmail(user.getEmail());
        tempUser.setLogin(user.getLogin());
        tempUser.setPassword(user.getPassword());
        tempUser.setCreatedDate(new Date());

        log.info("Update a user");
        userRepository.save(tempUser);
    }

    public Optional<User> findById(Long id) {
        log.info("Get an user id");
        return userRepository.findById(id);
    }

    public User findByLogin(String login) {
        log.info("Get an user by login");
        return userRepository.findByLogin(login);
    }

    public User findByEmail(String email) {
        log.info("Get an user by email");
        return userRepository.findByEmail(email);
    }
}
