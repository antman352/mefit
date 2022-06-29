package com.experis.mefit.controller;

import com.experis.mefit.model.User;
import com.experis.mefit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Represents a User Controller.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private HttpStatus httpStatus;

    /**
     * Get all Users.
     *
     * @return Iterable<User>
     */
    @GetMapping("list")
    public Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User returnUser = new User();
        HttpStatus status;

        if (userRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnUser = userRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnUser, status);
    }

    /**
     * Add a User.
     *
     * @param user : User
     * @return ResponseEntity<User>
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        httpStatus = HttpStatus.FORBIDDEN;
            try {
                user = userRepository.save(user);
                httpStatus = HttpStatus.CREATED;
            } catch (Exception e) {
                httpStatus = HttpStatus.BAD_REQUEST;
            }

        return new ResponseEntity<>(user, httpStatus);
    }

    /**
     * Update a User.
     *
     * @param id : Long
     * @param changedUser : User
     * @return ResponseEntity<User>
     */
    @PatchMapping("{id}")
    //@IsUser
    //@IsAdmin
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User changedUser) {
        User user = userRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;
        /*if (!Objects.equals(changedUser.getFirebaseUID(), user.getFirebaseUID())) {
            user.setFirebaseUID(changedUser.getFirebaseUID());
            needsUpdate = true;
        }*/
        if (needsUpdate) {
            userRepository.save(user);
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(user, httpStatus);
    }

    /**
     * Delete a User.
     *
     * @param id : Long
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
