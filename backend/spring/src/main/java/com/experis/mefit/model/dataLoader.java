package com.experis.mefit.model;

import com.experis.mefit.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class dataLoader implements ApplicationRunner {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    SetRepository setRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(exerciseRepository.findAll().size() != 0 && setRepository.findAll().size() != 0
                && workoutRepository.findAll().size() != 0) {
            return;
        }
        Set set1 = new Set("3x8");
        setRepository.save(set1);
        Set set2 = new Set("5x10");
        setRepository.save(set2);
        Set set3 = new Set("3x To Failure");
        setRepository.save(set3);
        Set set4 = new Set("3x15");
        setRepository.save(set4);


        Exercise exercise1 = new Exercise("Benchpress","Upper-body weight training exercise in which the trainee presses a weight upwards while lying on a weight training bench.",
                "Chest","Triceps","https://image.shutterstock.com/image-illustration/closegrip-barbell-bench-press-3d-260nw-430936051.jpg",
                "https://www.youtube.com/watch?v=SCVCLChPQFY");
        ArrayList<Set> setList1 = new ArrayList<>();
        setList1.add(set2);
        exercise1.setSet(setList1);
        exerciseRepository.save(exercise1);

        Exercise exercise2 = new Exercise("Deadlift","The deadlift is a weight training exercise in which a loaded barbell or bar is lifted off the ground.",
                "Glutes","Back","https://i.ytimg.com/vi/1ZXobu7JvvE/maxresdefault.jpg","https://www.youtube.com/watch?v=1ZXobu7JvvE");
        ArrayList<Set> setList2 = new ArrayList<>();
        setList2.add(set2);
        exercise2.setSet(setList2);
        exerciseRepository.save(exercise2);

        Exercise exercise3 = new Exercise("Shoulder Press","The shoulder press is a fairly common shoulder exercise that can be performed sitting or standing.",
                "Shoulder","Triceps","https://cdn2.coachmag.co.uk/sites/coachmag/files/2016/07/4-1-overhead-press.jpg",
                "https://www.youtube.com/watch?v=5yWaNOvgFCM");
        ArrayList<Set> setList3 = new ArrayList<>();
        setList3.add(set1);
        exercise3.setSet(setList3);
        exerciseRepository.save(exercise3);

        Exercise exercise4 = new Exercise("Sit ups","Situps are classic abdominal exercises done by lying on your back and lifting your torso.",
                "Core","-","https://cdn.shopify.com/s/files/1/1075/8446/files/exercise-18.jpg?0",
                "https://www.youtube.com/watch?v=1fbU_MkV7NE");
        ArrayList<Set> setList4 = new ArrayList<>();
        setList4.add(set3);
        exercise4.setSet(setList4);
        exerciseRepository.save(exercise4);

        Exercise exercise5 = new Exercise("Push ups","A push-up is a common calisthenics exercise beginning from the prone position. By raising and lowering the body using the arms, push-ups exercise the chest, triceps, and shoulders",
                "Chest","Triceps/Shoulders","http://www.marathonemma.se/wp-content/uploads/2014/09/armhav.jpg",
                "https://www.youtube.com/watch?v=_l3ySVKYVJ8");
        ArrayList<Set> setList5 = new ArrayList<>();
        setList4.add(set4);
        exercise5.setSet(setList5);
        exerciseRepository.save(exercise5);

        Exercise exercise6 = new Exercise("Bicepscurls","A full repetition consists of bending or curling the elbow until it is fully flexed, then slowly lowering the weight to the starting position.",
                "Biceps","Underarms/Shoulders","https://thumbs.dreamstime.com/z/exercising-ez-bar-curls-bodybuilding-target-muscles-marked-red-initial-final-steps-67126643.jpg","https://www.youtube.com/watch?v=dDI8ClxRS04");
        ArrayList<Set> setList6 = new ArrayList<>();
        setList6.add(set1);
        exercise6.setSet(setList6);
        exerciseRepository.save(exercise6);

        Exercise exercise7 = new Exercise("Dumbell Bicepscurls", "A full repetition consists of bending or curling the elbow until it is fully flexed, then slowly lowering the weight to the starting position.",
                "Biceps","Underarms/Shoulders","https://global-uploads.webflow.com/5d1d0d3f53ced35a29dbe169/5f88bbddf6cbe9edf0eefcad_supinated-dumbbell-curl.png",
                "https://www.youtube.com/watch?v=SB41wiGbkaw");
        ArrayList<Set> setList7 = new ArrayList<>();
        setList7.add(set3);
        exercise7.setSet(setList7);
        exerciseRepository.save(exercise7);

        Exercise exercise8 = new Exercise("Cable triceps push downs", "The triceps pushdown is one of the best exercises for triceps development. The versatile upper-body workout is done on a cable machine",
                "Tricpes","Underarms","https://cdn.shopify.com/s/files/1/1075/8446/files/exercise-45.jpg?0",
                "https://www.youtube.com/watch?v=6Fzep104f0s");
        ArrayList<Set> setList8 = new ArrayList<>();
        setList8.add(set2);
        exercise8.setSet(setList8);
        exerciseRepository.save(exercise8);

        Exercise exercise9 = new Exercise("Triceps dips", "The triceps dip exercise is a great bodyweight exercise that builds arm and shoulder strength.",
                "Tricpes","Chest","https://weighttraining.guide/wp-content/uploads/2016/05/Triceps-Dip-resized.png",
                "https://www.youtube.com/watch?v=wjUmnZH528Y");
        ArrayList<Set> setList9 = new ArrayList<>();
        setList9.add(set1);
        exercise9.setSet(setList9);
        exerciseRepository.save(exercise9);

        Exercise exercise11 = new Exercise("30 minutes run with 5 min warmup", "Running on a treadmill for 30 minutes with 5 minutes warmup. You choose tempo",
                "Cardio","-","-",
                "-");
        exerciseRepository.save(exercise11);


        Exercise exercise10 = new Exercise("Triceps dips", "The triceps dip exercise is a great bodyweight exercise that builds arm and shoulder strength.",
                "Tricpes","Chest","https://weighttraining.guide/wp-content/uploads/2016/05/Triceps-Dip-resized.png",
                "https://www.youtube.com/watch?v=wjUmnZH528Y");
        ArrayList<Set> setList10 = new ArrayList<>();
        setList10.add(set3);
        exercise10.setSet(setList10);
        exerciseRepository.save(exercise10);

        Exercise exercise12 = new Exercise("Body weight squats", "The bodyweight squat is a lower body strengthening exercise that can be performed virtually anywhere with no equipment and limited space. ",
                "Legs","Hamstrings","https://weighttraining.guide/wp-content/uploads/2018/07/Bodyweight-squat-resized.png",
                "https://www.youtube.com/watch?v=xKhd9MXTUzY");
        ArrayList<Set> setList12 = new ArrayList<>();
        setList12.add(set3);
        exercise12.setSet(setList12);
        exerciseRepository.save(exercise12);

        Exercise exercise13 = new Exercise("Incline leg press", "The incline leg press is a very effective compound exercise which builds muscle and strength in the leg muscles.",
                "Quadriceps","Hamstrings","https://i.pinimg.com/originals/2c/d7/b7/2cd7b737081ecdbfed26cbd12fc06937.jpg",
                "https://www.youtube.com/watch?v=U2AATwPoty0");
        ArrayList<Set> setList13 = new ArrayList<>();
        setList13.add(set3);
        exercise13.setSet(setList13);
        exerciseRepository.save(exercise13);

        Exercise exercise14 = new Exercise("30 min interval running", "Interval running on a treadmill for 30 minutes with 5 minutes warmup. 30 second fast and 2 minutes slow tempo",
                "Cardio","-","-",
                "-");
        exerciseRepository.save(exercise14);

        Exercise exercise15 = new Exercise("Cycling 30 minutes", "Cycling on a trainingcycle for 30 minutes with 5 minutes warmup. You choose tempo",
                "Cardio","-","-",
                "-");
        exerciseRepository.save(exercise15);

        Workout workout1 = new Workout("Discogym","Weight Lifting");
        ArrayList<Exercise> exerciseList1 = new ArrayList<>();
        exerciseList1.add(exercise1);
        exerciseList1.add(exercise2);
        workout1.setExercises(exerciseList1);
        workoutRepository.save(workout1);

        Workout workout2 = new Workout("Upperbody beginner","Weight lifting");
        ArrayList<Exercise> exerciseList2 = new ArrayList<>();
        exerciseList2.add(exercise1);
        exerciseList2.add(exercise2);
        exerciseList2.add(exercise3);
        exerciseList2.add(exercise4);
        exerciseList2.add(exercise6);
        workout2.setExercises(exerciseList2);
        workoutRepository.save(workout2);

        Workout workout3 = new Workout("Chest and biceps killer","Weight lifting");
        ArrayList<Exercise> exerciseList3 = new ArrayList<>();
        exerciseList3.add(exercise1);
        exerciseList3.add(exercise5);
        exerciseList3.add(exercise6);
        exerciseList3.add(exercise7);
        workout3.setExercises(exerciseList3);
        workoutRepository.save(workout3);

        Workout workout4 = new Workout("Chest and triceps","Weight lifting");
        ArrayList<Exercise> exerciseList4 = new ArrayList<>();
        exerciseList4.add(exercise1);
        exerciseList4.add(exercise5);
        exerciseList4.add(exercise8);
        exerciseList4.add(exercise9);
        workout4.setExercises(exerciseList4);
        workoutRepository.save(workout4);

        Workout workout5 = new Workout("Upperbody beginner 2","Weight lifting");
        ArrayList<Exercise> exerciseList5 = new ArrayList<>();
        exerciseList5.add(exercise2);
        exerciseList5.add(exercise3);
        exerciseList5.add(exercise5);
        exerciseList5.add(exercise7);
        exerciseList5.add(exercise9);
        workout5.setExercises(exerciseList5);
        workoutRepository.save(workout5);

        Workout workout6 = new Workout("Upperbody beginner 3","Weight lifting");
        ArrayList<Exercise> exerciseList6 = new ArrayList<>();
        exerciseList6.add(exercise3);
        exerciseList6.add(exercise4);
        exerciseList6.add(exercise5);
        exerciseList6.add(exercise6);
        exerciseList6.add(exercise9);
        workout6.setExercises(exerciseList6);
        workoutRepository.save(workout6);

        Workout workout7 = new Workout("Back and biceps pumper","Weight lifting");
        ArrayList<Exercise> exerciseList7 = new ArrayList<>();
        exerciseList7.add(exercise2);
        exerciseList7.add(exercise4);
        exerciseList7.add(exercise6);
        exerciseList7.add(exercise7);
        workout7.setExercises(exerciseList7);
        workoutRepository.save(workout7);

        Workout workout8 = new Workout("Shoulder and legs killer","Weight lifting");
        ArrayList<Exercise> exerciseList8 = new ArrayList<>();
        exerciseList8.add(exercise2);
        exerciseList8.add(exercise3);
        exerciseList8.add(exercise12);
        exerciseList8.add(exercise13);
        workout8.setExercises(exerciseList8);
        workoutRepository.save(workout8);

        Workout workout9 = new Workout("Cardio and abs 1","Cardio");
        ArrayList<Exercise> exerciseList9 = new ArrayList<>();
        exerciseList9.add(exercise4);
        exerciseList9.add(exercise11);
        workout9.setExercises(exerciseList9);
        workoutRepository.save(workout9);

        Workout workout10 = new Workout("Cardio and abs 2","Cardio");
        ArrayList<Exercise> exerciseList10 = new ArrayList<>();
        exerciseList10.add(exercise4);
        exerciseList10.add(exercise14);
        workout10.setExercises(exerciseList10);
        workoutRepository.save(workout10);

        Workout workout11 = new Workout("Cardio and abs 3","Cardio");
        ArrayList<Exercise> exerciseList11 = new ArrayList<>();
        exerciseList11.add(exercise4);
        exerciseList11.add(exercise15);
        workout11.setExercises(exerciseList11);
        workoutRepository.save(workout11);

        Program program = new Program("MeFit beginner","Gym");
        ArrayList<Workout> workoutList = new ArrayList<>();
        workoutList.add(workout2);
        workoutList.add(workout5);
        workoutList.add(workout6);
        program.setWorkouts(workoutList);
        programRepository.save(program);

        Program program2 = new Program("MeFit All around","Gym");
        ArrayList<Workout> workoutList2 = new ArrayList<>();
        workoutList2.add(workout4);
        workoutList2.add(workout7);
        workoutList2.add(workout8);
        workoutList2.add(workout9);
        program2.setWorkouts(workoutList2);
        programRepository.save(program2);

        Program program3 = new Program("The runner","Running");
        ArrayList<Workout> workoutList3 = new ArrayList<>();
        workoutList3.add(workout9);
        workoutList3.add(workout10);
        workoutList3.add(workout11);
        program3.setWorkouts(workoutList3);
        programRepository.save(program3);

        Program program4 = new Program("Antes favourites","Gym");
        ArrayList<Workout> workoutList4 = new ArrayList<>();
        workoutList4.add(workout1);
        workoutList4.add(workout4);
        workoutList4.add(workout10);
        program4.setWorkouts(workoutList4);
        programRepository.save(program4);
    }
}