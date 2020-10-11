package model;

import model.Meal;
import model.Exercise;

import java.util.ArrayList;


public class Day {
    private ArrayList<Meal> listOfM;
    private ArrayList<Exercise> listOfE;

    public Day() {
        listOfE = new ArrayList<Exercise>();
        listOfM = new ArrayList<Meal>();
    }

    public void addMeal(String name, String description, int calories) {
        listOfM.add(new Meal(name, description, calories));
    }

    public void addMeal(String name, int calories) {
        listOfM.add(new Meal(name, calories));
    }

    public void addExercise(String name, String description, int calories) {
        listOfE.add(new Exercise(name, description, calories));
    }

    public void addExercise(String name, int calories) {
        listOfE.add(new Exercise(name, calories));
    }

    public int caloriesIn() {
        int total = 0;
        for (Meal m:listOfM) {
            total += m.getCalories();
        }
        return total;
    }

    public int caloriesOut() {
        int total = 0;
        for (Exercise e:listOfE) {
            total += e.getCalories();
        }
        return total;
    }

    public int caloricTotal() {
        return this.caloriesIn() - this.caloriesOut();
    }
}
