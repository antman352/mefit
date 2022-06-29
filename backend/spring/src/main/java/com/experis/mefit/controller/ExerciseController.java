package com.experis.mefit.controller;

import com.experis.mefit.model.Exercise;
import com.experis.mefit.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Represents a Exercise Controller.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;
    private HttpStatus httpStatus;

    /**
     * Get all Exercises.
     *
     * @return Iterable<Exercise>
     */
    @GetMapping("list")
    //@IsUserOrContributor or @AllRoles
    public Iterable<Exercise> getExercises() {
        return this.exerciseRepository.findAll();
    }

    /**
     * Add a Exercise.
     *
     * @param exercise : Exercise
     * @return ResponseEntity<Exercise>
     */
    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        try {
            exercise = exerciseRepository.save(exercise);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(exercise, httpStatus);
    }

    /**
     * Get a Exercise by id.
     *
     * @param id : Long
     * @return ResponseEntity<Exercise>
     */
    @GetMapping("{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable Long id) {
        Exercise exercise = new Exercise();
        if (exerciseRepository.existsById(id)) {
            httpStatus = HttpStatus.OK;
            exercise = exerciseRepository.findById(id).get();
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(exercise, httpStatus);
    }

    /**
     * Update a Exercise.
     *
     * @param id : Long
     * @param changedExercise : Exercise
     * @return ResponseEntity<Exercise>
     */
    @PatchMapping("{id}")
    //Lägg till att man har koll på vem som gjort exercises då bara den contributern ska kunna ändra på exercisesen.
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise changedExercise) {
        //TODO: Is this really PATCH and not PUT? We need to check this
        Exercise exercise = exerciseRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;

        if (!Objects.equals(changedExercise.getName(), exercise.getName())) {
            exercise.setName(changedExercise.getName());
            needsUpdate = true;
        }
        if (!Objects.equals(changedExercise.getDescription(), exercise.getDescription())) {
            exercise.setDescription((changedExercise.getDescription()));
            needsUpdate = true;
        }
        if (!Objects.equals(changedExercise.getPrimaryTargetMuscleGroup(), exercise.getPrimaryTargetMuscleGroup())) {
            exercise.setPrimaryTargetMuscleGroup((changedExercise.getPrimaryTargetMuscleGroup()));
            needsUpdate = true;
        }
        if (!Objects.equals(changedExercise.getSecondaryTargetMuscleGroup(), exercise.getSecondaryTargetMuscleGroup())) {
            exercise.setSecondaryTargetMuscleGroup((changedExercise.getSecondaryTargetMuscleGroup()));
            needsUpdate = true;
        }
        if (!Objects.equals(changedExercise.getImage(), exercise.getImage())) {
            exercise.setImage((changedExercise.getImage()));
            needsUpdate = true;
        }
        if (!Objects.equals(changedExercise.getVideoLink(), exercise.getVideoLink())) {
            exercise.setVideoLink((changedExercise.getVideoLink()));
            needsUpdate = true;
        }
        if (needsUpdate)
            exerciseRepository.save(exercise);

        return new ResponseEntity<>(exercise, httpStatus);
    }

    /**
     * Delete a Exercise.
     *
     * @param id : Long
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("{id}")
    //Lägg till att man har koll på vem som gjort exercises då bara den contributern ska kunna radera på exercisesen.
    public ResponseEntity<Exercise> deleteExercise(@PathVariable Long id) {
        if (exerciseRepository.existsById(id)) {
            exerciseRepository.deleteById(id);
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
