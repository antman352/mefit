package com.experis.mefit.controller;

import com.experis.mefit.model.Set;
import com.experis.mefit.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Represents a Set Controller.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/sets")
public class SetController {

    @Autowired
    private SetRepository setRepository;
    private HttpStatus httpStatus;

    /**
     * Get all Sets.
     *
     * @return Iterable<Set>
     */
    @GetMapping("list")
    public Iterable<Set> getSets() {
        return this.setRepository.findAll();
    }

    /**
     * Add a Set.
     *
     * @param set : Set
     * @return ResponseEntity<Set>
     */
    @PostMapping
    public ResponseEntity<Set> addSet(@RequestBody Set set) {
        try {
            set = setRepository.save(set);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(set, httpStatus);
    }

    /**
     * Get a Set by id.
     *
     * @param id : Long
     * @return ResponseEntity<Set>
     */
    @GetMapping("{id}")
    public ResponseEntity<Set> getSet(@PathVariable Long id) {
        Set set = new Set();
        if (setRepository.existsById(id)) {
            httpStatus = HttpStatus.OK;
            set = setRepository.findById(id).get();
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(set, httpStatus);
    }

    /**
     * Update a Set.
     *
     * @param id : Long
     * @param changedSet : Set
     * @return ResponseEntity<Set>
     */
    //TODO: Is this really PATCH and not PUT? We need to check this
    @PatchMapping("{id}")
    public ResponseEntity<Set> updateSet(@PathVariable Long id, @RequestBody Set changedSet) {
        Set set = setRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;

        if (!Objects.equals(changedSet.getExerciseRepetitions(), set.getExerciseRepetitions())) {
            set.setExerciseRepetitions(changedSet.getExerciseRepetitions());
            needsUpdate = true;
        }
        if (needsUpdate)
            setRepository.save(set);

        return new ResponseEntity<>(set, httpStatus);
    }

    /**
     * Delete a Set.
     *
     * @param id : Long
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Set> deleteSet(@PathVariable Long id) {
        if (setRepository.existsById(id)) {
            setRepository.deleteById(id);
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
