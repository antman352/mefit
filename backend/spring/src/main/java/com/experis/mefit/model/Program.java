package com.experis.mefit.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an Program entity.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String category;

    public Program(String name, String category) {
        this.name = name;
        this.category = category;
    }
    public Program() {

    }

    @OneToMany(mappedBy = "program")
    List<Goal> goal;

    @JsonGetter("goal")
    public List<String> goals() {
        if (goal != null) {
            return goal.stream()
                    .map(goal -> {
                        return "api/v1/goals/" + goal.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    /*@ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;*/

    /*@OneToOne(mappedBy = "program", fetch = FetchType.LAZY)
    private Goal goal;

    @JsonGetter("goal")
    public String goal() {
        if (goal != null) {
            return "/api/v1/goals/" + goal.getId();
        }
        return null;
    }*/

    @ManyToMany
    @JoinTable(
            name = "program_workout",
            joinColumns = {@JoinColumn(name = "program_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )
    public List<Workout> workouts = new ArrayList<>();

    /*@JsonGetter("workouts")
    public List<Workout> workouts() {
        if (workouts != null) {
            return workouts.stream().collect(Collectors.toList());
        }
        return null;
    }*/

    public List<Goal> getGoal() {
        return goal;
    }

    public void setGoal(List<Goal> goal) {
        this.goal = goal;
    }

    /*public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }*/

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /*public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }*/

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}
