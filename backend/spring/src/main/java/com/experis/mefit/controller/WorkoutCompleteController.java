package com.experis.mefit.controller;

import com.experis.mefit.model.Workout;
import com.experis.mefit.model.WorkoutComplete;
import com.experis.mefit.repository.WorkoutCompleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/v1/completes")
public class WorkoutCompleteController {

    private HttpStatus httpStatus;

    @Autowired
    private WorkoutCompleteRepository workoutCompleteRepository;

    @PatchMapping("{id}")
    // @IsContributor
    public ResponseEntity<WorkoutComplete> updateWorkout(@PathVariable Long id, @RequestBody WorkoutComplete completedWorkout) {
        //TODO: Is this really PATCH and not PUT? We need to check this
        WorkoutComplete workout = workoutCompleteRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;

        if (!Objects.equals(completedWorkout.isCompleted(), workout.isCompleted())) {
            workout.setCompleted(completedWorkout.isCompleted());
            needsUpdate = true;
        }

        /*if (!Objects.equals(changedWorkout.isComplete(), workout.isComplete())) {
            workout.setComplete((changedWorkout.isComplete()));
            needsUpdate = true;
        }*/
        if (needsUpdate) workoutCompleteRepository.save(workout);
        return new ResponseEntity<>(workout, httpStatus);
    }
}