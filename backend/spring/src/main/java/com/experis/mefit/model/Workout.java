package com.experis.mefit.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an Workout entity.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;
    //private boolean complete;

    public Workout(String name, String type) {
        this.name = name;
        this.type = type;
        //this.complete = complete;
    }
    public Workout(){}

    @ManyToMany
    @JoinTable(
            name = "program_workout",
            joinColumns = {@JoinColumn(name = "workout_id")},
            inverseJoinColumns = {@JoinColumn(name = "program_id")}
    )
    public List<Program> programs = new ArrayList<>();

    @JsonGetter("programs")
    public List<String> programs() {
        if (programs != null) {
            return programs.stream()
                    .map(program -> {
                        return "/api/v1/goals/" + program.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    /*@ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonGetter("profile")
    public String profile() {
        if (profile != null) {
            return "/api/v1/profiles/" + profile.getId();
        }
        return null;
    }*/

    /*@ManyToMany
    @JoinTable(
            name = "goal_workout",
            joinColumns = {@JoinColumn(name = "workout_id")},
            inverseJoinColumns = {@JoinColumn(name = "goal_id")}
    )
    public List<Goal> goals = new ArrayList<>();

    @JsonGetter("goals")
    public List<String> goals() {
        if (goals != null) {
            return goals.stream()
                    .map(goal -> {
                        return "/api/v1/goals/" + goal.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }*/

    @ManyToMany
    @JoinTable(
            name = "workout_exercise",
            joinColumns = {@JoinColumn(name = "workout_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id")}
    )
    public List<Exercise> exercises = new ArrayList<>();

    @JsonGetter("exercises")
    public List<Exercise> exercises() {
        if (exercises != null) {
            return exercises.stream().collect(Collectors.toList());
        }
        return null;
    }

    @OneToMany(mappedBy = "workout")
    List<WorkoutComplete> workoutsCompleted;

    @JsonGetter("workoutsCompleted")
    public List<WorkoutComplete> workoutsCompleted() {
        if (workoutsCompleted != null) {
            return workoutsCompleted.stream().collect(Collectors.toList());
        }
        return null;
    }

    public List<WorkoutComplete> getWorkoutsCompleted() {
        return workoutsCompleted;
    }

    public void setWorkoutsCompleted(List<WorkoutComplete> workoutsCompleted) {
        this.workoutsCompleted = workoutsCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }*/

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    /*public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }*/

    /*public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }*/

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
