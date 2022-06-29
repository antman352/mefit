package com.experis.mefit.controller;

import com.experis.mefit.model.Goal;
import com.experis.mefit.model.Program;
import com.experis.mefit.model.WorkoutComplete;
import com.experis.mefit.repository.GoalRepository;
import com.experis.mefit.repository.ProgramRepository;
import com.experis.mefit.repository.WorkoutCompleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Goal Controller.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/goals")
public class GoalController {

    @Autowired
    private GoalRepository goalRepository;
    private HttpStatus httpStatus;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private WorkoutCompleteRepository workoutCompleteRepository;

    /**
     *
     * Get all Goals.
     *
     * @return Iterable<Goal>
     */
    /*@GetMapping("list")
    @IsUser
    public Iterable<Goal> getGoals() {
        return this.goalRepository.findAll();
    }*/

    /**
     * Add a Goal.
     *
     * @param goal : Goal
     * @return ResponseEntity<Goal>
     */
    @PostMapping
    public ResponseEntity<Goal> addGoal(@RequestBody Goal goal) {
        try {
            goal = goalRepository.save(goal);
            httpStatus = HttpStatus.CREATED;
            System.out.println(goal.getProgram());
            System.out.println(goal.getProgram().getWorkouts());
            System.out.println(goal.getId());

            Program program = programRepository.findById(goal.getProgram().getId()).get();

            for(int i=0; i<program.getWorkouts().size(); i++) {
                WorkoutComplete workoutComplete = new WorkoutComplete();
                workoutComplete.setCompleted(false);
                workoutComplete.setWorkout(program.getWorkouts().get(i));
                //workoutComplete.setProfile(goal.getProfile());
                workoutComplete.setGoal(goal);
                //System.out.println(workoutComplete.getProfile());
                workoutCompleteRepository.save(workoutComplete);
            }

        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(goal, httpStatus);
    }

    /**
     * Get a Goal by id.
     *
     * @param id : Long
     * @return ResponseEntity<Goal>
     */
    @GetMapping("{id}")
    public ResponseEntity<Goal> getGoal(@PathVariable Long id) {
        Goal goal = new Goal();
        if (goalRepository.existsById(id)) {
            httpStatus = HttpStatus.OK;
            goal = goalRepository.findById(id).get();
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(goal, httpStatus);
    }

    @GetMapping("/completed/{id}")
    public ResponseEntity<List<WorkoutComplete>> getCompletedWorkouts(@PathVariable Long id) {
        List<WorkoutComplete> workoutComplete = workoutCompleteRepository.findAll();
        List<WorkoutComplete> workoutCompleteById = new ArrayList<>();
        for(int i =0; i<workoutComplete.size(); i++) {
            if(workoutComplete.get(i).goal() == id) {
                workoutCompleteById.add(workoutComplete.get(i));
                httpStatus = HttpStatus.OK;
            }
        }
        return new ResponseEntity<>(workoutCompleteById,httpStatus);
    }

    @GetMapping("/program/{id}")
    public ResponseEntity<Program> getProgramWithinGoal(@PathVariable Long id) {
        Program program = new Program();
        HttpStatus status;

        if (goalRepository.existsById(id)) {
            status = HttpStatus.OK;
            program = goalRepository.findById(id).get().getProgram();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(program, status);
    }
    /**
     * Update a Goal.
     *
     * @param id : Long
     * @param changedGoal : Goal
     * @return ResponseEntity<Goal>
     */
    @PatchMapping("{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable Long id, @RequestBody Goal changedGoal) {
        Goal goal = goalRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;

        if (!Objects.equals(changedGoal.getEndDate(), goal.getEndDate()) && changedGoal.getEndDate() != null) {
            goal.setEndDate(changedGoal.getEndDate());
            needsUpdate = true;
        }
        if (!Objects.equals(changedGoal.getStartDate(), goal.getStartDate()) && changedGoal.getStartDate() != null) {
            goal.setStartDate(changedGoal.getStartDate());
            needsUpdate = true;
        }
        if (!Objects.equals(changedGoal.isAchieved(), goal.isAchieved())) {
            goal.setAchieved((changedGoal.isAchieved()));
            needsUpdate = true;
        }
        if (needsUpdate)
            goalRepository.save(goal);

        return new ResponseEntity<>(goal, httpStatus);
    }

    /**
     * Delete a Goal.
     *
     * @param id : Long
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable Long id) {
        if (goalRepository.existsById(id)) {
            goalRepository.deleteById(id);
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
