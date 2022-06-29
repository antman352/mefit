package com.experis.mefit.model;


import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;

@Entity
public class WorkoutComplete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    Workout workout;

    @JsonGetter("workout")
    public Long workout() {
        if (workout != null) {
            return workout.getId();
        }
        return null;
    }

    /*@ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;*/

    @ManyToOne
    @JoinColumn(name ="goal_id")
    Goal goal;

    @JsonGetter("goal")
    public Long goal() {
        if (goal != null) {
            return goal.getId();
        }
        return null;
    }

    boolean completed;

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    /*public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }*/

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
