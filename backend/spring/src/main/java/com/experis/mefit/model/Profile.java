package com.experis.mefit.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;

/**
 * Represents an Profile entity.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long weight;
    private Long height;

    @Column(name = "medical_conditions")
    private String medicalConditions;

    private String disabilities;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonGetter("user")
    public String user() {
        if (user != null) {
            return "/api/v1/users/" + user.getId();
        }
        return null;
    }

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    //@OneToMany(mappedBy = "profile")
    //List<Program> programs;

    @OneToMany(mappedBy = "profile")
    List<Goal> goals;

    //@OneToMany(mappedBy = "profile")
   // List<Workout> workouts;

    //@OneToMany(mappedBy = "profile")
    //List<WorkoutComplete> workoutsCompleted;

    /*public List<WorkoutComplete> getWorkoutsCompleted() {
        return workoutsCompleted;
    }

    public void setWorkoutsCompleted(List<WorkoutComplete> workoutsCompleted) {
        this.workoutsCompleted = workoutsCompleted;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }*/

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public String getMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(String medicalConditions) {
        this.medicalConditions = medicalConditions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /*public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getMedical_conditions() {
        return medicalConditions;
    }

    public void setMedical_conditions(String medicalConditions) {
        this.medicalConditions = medicalConditions;
    }

    public String getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(String disabilities) {
        this.disabilities = disabilities;
    }
}
