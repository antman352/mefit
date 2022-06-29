package com.experis.mefit.controller;

import com.experis.mefit.model.Goal;
import com.experis.mefit.model.Program;
import com.experis.mefit.model.Workout;
import com.experis.mefit.repository.GoalRepository;
import com.experis.mefit.repository.ProgramRepository;
import com.experis.mefit.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Program Controller.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/programs")
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;
    private HttpStatus httpStatus;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    /**
     * Get all Programs.
     *
     * @return Iterable<Program>
     */
    @GetMapping("list")
    public Iterable<Program> getPrograms() {
        return this.programRepository.findAll();
    }

    /**
     * Add a Program.
     *
     * @param program : Program
     * @return ResponseEntity<Program>
     */
    @PostMapping
    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
        try {
            program = programRepository.save(program);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(program, httpStatus);
    }

    /**
     * Get a Program by id.
     *
     * @param id : Long
     * @return ResponseEntity<Program>
     */
    @GetMapping("{id}")
    public ResponseEntity<Program> getProgram(@PathVariable Long id) {
        Program program = new Program();
        if (programRepository.existsById(id)) {
            httpStatus = HttpStatus.OK;
            program = programRepository.findById(id).get();
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(program, httpStatus);
    }

    @GetMapping("/workouts/{id}")
    public ResponseEntity<List<Workout>> getWorkoutsWithinProgram(@PathVariable Long id) {
        List<Workout> workoutList = new ArrayList<>();
        HttpStatus status;

        if (programRepository.existsById(id)) {
            status = HttpStatus.OK;
            workoutList = programRepository.findById(id).get().getWorkouts();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(workoutList, status);
    }

    /**
     * Update a Program.
     *
     * @param id : Long
     * @param changedProgram : Program
     * @return ResponseEntity<Program>
     */
    //TODO: Is this really PATCH and not PUT? We need to check this
    @PatchMapping("{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable Long id, @RequestBody Program changedProgram) {
        Program program = programRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;

        if (!Objects.equals(changedProgram.getName(), program.getName())) {
            program.setName(changedProgram.getName());
            needsUpdate = true;
        }
        if (!Objects.equals(changedProgram.getCategory(), program.getCategory())) {
            program.setCategory((changedProgram.getCategory()));
            needsUpdate = true;
        }
        if (needsUpdate)
            programRepository.save(program);

        return new ResponseEntity<>(program, httpStatus);
    }

    /**
     * Delete a Program.
     *
     * @param id : Long
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Program> deleteProgram(@PathVariable Long id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(httpStatus);
    }
}