package com.experis.mefit.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an Goal entity.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "achieved")
    private boolean achieved;

    /*@ManyToMany
    @JoinTable(
            name = "goal_workout",
            joinColumns = {@JoinColumn(name = "goal_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )
    public List<Workout> workouts = new ArrayList<>();*/

    /*@OneToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonGetter("program")
    public String program() {
        if (program != null) {
            return "/api/v1/programs/" + program.getId();
        }
        return null;
    }*/

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    /*@JsonGetter("program")
    public Program program() {
        if (program != null) {
            return program;
        } else {
            return null;
        }
    }*/

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonGetter("profile")
    public String profile() {
        if (profile != null) {
            return "/api/v1/profiles/" + profile.getId();
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

    /*public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
    */

    public List<WorkoutComplete> getWorkoutsCompleted() {
        return workoutsCompleted;
    }

    public void setWorkoutsCompleted(List<WorkoutComplete> workoutsCompleted) {
        this.workoutsCompleted = workoutsCompleted;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
