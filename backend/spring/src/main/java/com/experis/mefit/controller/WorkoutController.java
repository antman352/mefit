package com.experis.mefit.controller;

import com.experis.mefit.model.Exercise;
import com.experis.mefit.model.Workout;
import com.experis.mefit.model.WorkoutComplete;
import com.experis.mefit.repository.ExerciseRepository;
import com.experis.mefit.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Workout Controller.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;
    private HttpStatus httpStatus;

    @Autowired
    private ExerciseRepository exerciseRepository;

    /**
     * Get all Workouts.
     *
     * @return Iterable<Workout>0
     */
    @GetMapping("list")
    public Iterable<Workout> getWorkouts() {
        return this.workoutRepository.findAll();
    }

    /**
     * Add a Workout.
     *
     * @param workout : Workout
     * @return ResponseEntity<Workout>
     */
    @PostMapping
    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout) {
        try {
            workout = workoutRepository.save(workout);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(workout, httpStatus);
    }

    /**
     * Get a Workout by id.
     *
     * @param id : Long
     * @return ResponseEntity<Workout>
     */
    @GetMapping("{id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable Long id) {
        Workout workout = new Workout();
        if (workoutRepository.existsById(id)) {
            httpStatus = HttpStatus.OK;
            workout = workoutRepository.findById(id).get();
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(workout, httpStatus);
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<List<Exercise>> getExercisesWithinWorkout(@PathVariable Long id) {
        List<Exercise> exerciseList = new ArrayList<>();
        HttpStatus status;

        if (workoutRepository.existsById(id)) {
            status = HttpStatus.OK;
            exerciseList = workoutRepository.findById(id).get().getExercises();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(exerciseList, status);
    }

    //@PostMapping
    //public ResponseEntity<WorkoutComplete> addCompletedWorkout(@RequestBody )

    /**
     * Update a Workout.
     *
     * @param id : Long
     * @param changedWorkout : Workout
     * @return ResponseEntity<Workout>
     */
    @PatchMapping("{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout changedWorkout) {
        //TODO: Is this really PATCH and not PUT? We need to check this
        Workout workout = workoutRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;

        if (!Objects.equals(changedWorkout.getName(), workout.getName()) && changedWorkout.getName() != null) {
            workout.setName(changedWorkout.getName());
            needsUpdate = true;
        }
        if (!Objects.equals(changedWorkout.getType(), workout.getType()) && changedWorkout.getName() != null) {
            workout.setType((changedWorkout.getType()));
            needsUpdate = true;
        }
        /*if (!Objects.equals(changedWorkout.isComplete(), workout.isComplete())) {
            workout.setComplete((changedWorkout.isComplete()));
            needsUpdate = true;
        }*/
        if (needsUpdate)
            workoutRepository.save(workout);

        return new ResponseEntity<>(workout, httpStatus);
    }

    /**
     * Delete a Workout.
     *
     * @param id : Long
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Workout> deleteWorkout(@PathVariable Long id) {
        if (workoutRepository.existsById(id)) {
            workoutRepository.deleteById(id);
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
