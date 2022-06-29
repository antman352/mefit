package com.experis.mefit.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an Exercises entity.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @Column(name = "primary_target_muscle_group")
    private String primaryTargetMuscleGroup;

    @Column(name = "secondary_target_muscle_group")
    private String secondaryTargetMuscleGroup;


    private String image;

    @Column(name = "video_link")
    private String videoLink;

    public Exercise(String name, String description, String secondaryTargetMuscleGroup,String primaryTargetMuscleGroup, String image, String videoLink) {
        this.name = name;
        this.description = description;
        this.secondaryTargetMuscleGroup = secondaryTargetMuscleGroup;
        this.primaryTargetMuscleGroup = primaryTargetMuscleGroup;
        this.image = image;
        this.videoLink = videoLink;
    }

    public Exercise() {

    }
    @ManyToMany
    @JoinTable(
            name = "workout_exercise",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )
    public List<Workout> workouts = new ArrayList<>();

    @JsonGetter("workouts")
    public List<String> workouts() {
        if (workouts != null) {
            return workouts.stream()
                    .map(workout -> {
                        return "/api/v1/workouts/" + workout.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    //@OneToMany(mappedBy = "exercise")
    //List<Set> set;

    @ManyToMany
    @JoinTable(
            name = "exercise_set",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "set_id")}
    )
    public List<Set> sets = new ArrayList<>();

    @JsonGetter("set")
    public List<Set> sets() {
        if (sets != null) {
            return sets.stream().collect(Collectors.toList());
        }
        return null;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getPrimaryTargetMuscleGroup() {
        return primaryTargetMuscleGroup;
    }

    public void setPrimaryTargetMuscleGroup(String primaryTargetMuscleGroup) {
        this.primaryTargetMuscleGroup = primaryTargetMuscleGroup;
    }

    public String getSecondaryTargetMuscleGroup() {
        return secondaryTargetMuscleGroup;
    }

    public void setSecondaryTargetMuscleGroup(String secondaryTargetMuscleGroup) {
        this.secondaryTargetMuscleGroup = secondaryTargetMuscleGroup;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public List<Set> getSet() {
        return sets;
    }

    public void setSet(List<Set> set) {
        this.sets = set;
    }
}
