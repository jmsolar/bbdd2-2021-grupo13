package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.User;
import com.grupo13.elasticSearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param email email del usuario
     * @return
     */
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    /**
     *
     * @param email email del usuario con el cual ingresa al sitio
     * @param fullname nombre y apellido del usuario
     * @param password clave con la que el usuario ingresa al sitio
     * @param dayOfBirth fecha de nacimiento del usuario
     * @return el usuario creado
     * @throws ElasticSearchException
     */
    public User create(String email, String fullname, String password, Date dayOfBirth) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByEmail(email).isPresent()) ex.constraintViolation();

        User newUser = new User(email, fullname, password, dayOfBirth);
        this.userRepository.save(newUser);

        return newUser;
    }
}
