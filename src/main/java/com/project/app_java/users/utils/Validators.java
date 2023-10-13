package com.project.app_java.users.utils;

import com.project.app_java.shared.exceptions.AlreadyExistsHttpException;
import com.project.app_java.users.models.User;
import com.project.app_java.users.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class Validators {
    private UserRepository userRepository;
    public Validators(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * method for validate if exists user by name or fail
     * @param name
     * @return void
     * */
    public void validateIfExistsUserByNameOrFail(String name) throws AlreadyExistsHttpException {
        User user = userRepository.findByName(name);
        if(user != null) {
            throw new AlreadyExistsHttpException("User already exists");
        }
    }
    /**
     * method for validate if exists user by email or fail
     * @param email
     * @return void
     * */
    public void validateIfExistsUserByEmailOrFail(String email) throws AlreadyExistsHttpException {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            throw new AlreadyExistsHttpException("User already exists");
        }
    }

    // TODO: add more validators
}
