package com.experis.mefit.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an Set entity.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@Entity
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "exercise_repetitions")
    private String exerciseRepetitions;

    public Set(String exerciseRepetitions) {
        this.exerciseRepetitions = exerciseRepetitions;
    }
    public Set() {
    }

    /*@ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;*/

    @ManyToMany
    @JoinTable(
            name = "exercise_set",
            joinColumns = {@JoinColumn(name = "set_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id")}
    )
    public List<Exercise> exercises = new ArrayList<>();

    @JsonGetter("exercises")
    public List<String> exercises() {
        if (exercises != null) {
            return exercises.stream()
                    .map(exercise -> {
                        return "/api/v1/exercises/" + exercise.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getExerciseRepetitions() {
        return exerciseRepetitions;
    }

    public void setExerciseRepetitions(String exerciseRepetitions) {
        this.exerciseRepetitions = exerciseRepetitions;
    }


}
