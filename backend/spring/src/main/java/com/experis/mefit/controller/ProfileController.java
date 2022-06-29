package com.experis.mefit.controller;

import com.experis.mefit.model.Profile;
import com.experis.mefit.model.User;
import com.experis.mefit.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Represents a Profile Controller.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;
    private HttpStatus httpStatus;

//    /**
//     * Get all Profiles.
//     *
//     * @return Iterable<Profile>
//     */
//    @GetMapping("list")
//
//    public Iterable<Profile> getProfiles() {
//        return this.profileRepository.findAll();
//    }

    /**
     * Add a Profile.
     *
     * @param profile : Profile
     * @return ResponseEntity<Profile>
     */
    @PostMapping
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile) {
        try {
            profile = profileRepository.save(profile);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(profile, httpStatus);
    }

    /**
     * Get a Profile by id.
     *
     * @param id : Long
     * @return ResponseEntity<Profile>
     */
    @GetMapping("{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        Profile profile = new Profile();
        if (profileRepository.existsById(id)) {
            httpStatus = HttpStatus.OK;
            profile = profileRepository.findById(id).get();
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(profile, httpStatus);
    }

    /**
     * Update a Profile.
     *
     * @param id : Long
     * @param changedProfile : Profile
     * @return ResponseEntity<Profile>
     */
    //TODO: Is this really PATCH and not PUT? We need to check this
    @PatchMapping("{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile changedProfile) {
        Profile profile = profileRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;

        if (!Objects.equals(changedProfile.getWeight(), profile.getWeight())) {
            profile.setWeight(changedProfile.getWeight());
            needsUpdate = true;
        }
        if (!Objects.equals(changedProfile.getHeight(), profile.getHeight())) {
            profile.setHeight((changedProfile.getHeight()));
            needsUpdate = true;
        }
        if (!Objects.equals(changedProfile.getMedical_conditions(), profile.getMedical_conditions())) {
            profile.setMedical_conditions(changedProfile.getMedical_conditions());
            needsUpdate = true;
        }
        if (!Objects.equals(changedProfile.getDisabilities(), profile.getDisabilities())) {
            profile.setDisabilities(changedProfile.getDisabilities());
            needsUpdate = true;
        }
        if (needsUpdate)
            profileRepository.save(profile);

        return new ResponseEntity<>(profile, httpStatus);
    }

    /**
     * Delete a Profile.
     *
     * @param id : Long
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteProfile(@PathVariable Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
