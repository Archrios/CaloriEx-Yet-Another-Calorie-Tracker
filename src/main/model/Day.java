package model;

import com.sun.jmx.remote.internal.RMIExporter;

import java.util.ArrayList;
import java.util.Calendar;

//Represents a day which contains a list of meals eaten and a list of exercises done
public class Day {
    private ArrayList<Meal> listOfM;
    private ArrayList<Exercise> listOfE;
    private Calendar date = Calendar.getInstance();

    // EFFECTS: Constructs a day with empty list of exercises and meals, with the date as the current date
    public Day() {
        listOfE = new ArrayList<Exercise>();
        listOfM = new ArrayList<Meal>();
    }

    //REQUIRES: MONTH is between 1 and 12, day is between 1 and 31, and actually fits according to the month
    // nothing like February the 30th
    // EFFECTS: Constructs a day with empty list of exercises and meals, with the date based on user input
    public Day(int year, int month, int day) {
        listOfE = new ArrayList<Exercise>();
        listOfM = new ArrayList<Meal>();
        //Java is weird and starts months with 0; Jan is 0, and Dec is 11 so its easier to do so here
        //than telling user to manually offset
        date.set(year, month - 1, day);
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: adds a Meal to the list of Meals with a name, description, and number of calories
    public void addMeal(String name, String description, int calories) {
        listOfM.add(new Meal(name, description, calories));
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: adds a Meal to the list of Meals with a name, and number of calories
    public void addMeal(String name, int calories) {
        listOfM.add(new Meal(name, calories));
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: adds an Exercise to the list of Exercise with a name, description, and calories burned
    public void addExercise(String name, String description, int calories) {
        listOfE.add(new Exercise(name, description, calories));
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: adds an Exercise to the list of Exercise with a name, and calories burned
    public void addExercise(String name, int calories) {
        listOfE.add(new Exercise(name, calories));
    }

    // EFFECTS: returns the total number of calories consumed today
    public int caloriesIn() {
        int total = 0;
        for (Meal m:listOfM) {
            total += m.getCalories();
        }
        return total;
    }

    // EFFECTS: returns the total number of calories burned today
    public int caloriesOut() {
        int total = 0;
        for (Exercise e:listOfE) {
            total += e.getCalories();
        }
        return total;
    }

    // EFFECTS: returns the net total of calories consumed today
    public int caloricTotal() {
        return this.caloriesIn() - this.caloriesOut();
    }

    // EFFECTS: returns a formatted list of the names of all the Exercises done today based on order of entry
    public String exerciseNames() {
        String exercises = "";
        for (Exercise e:listOfE) {
            exercises += e.getName();
            exercises += "\n";
        }
        return exercises;
    }

    // EFFECTS: returns a formatted list of the names of all the Meals eaten today based on order of entry
    public String mealNames() {
        String meals = "";
        for (Meal m:listOfM) {
            meals += m.getName();
            meals += "\n";
        }
        return meals;
    }

    //EFFECTS: returns a formatted list of names, calories, and desc (if it has one) of all Meals eaten today
    // based on order of entry
    public String mealAllDetails() {
        String meals = "";
        for (Meal m:listOfM) {
            meals += (m.getName() + ": " + m.getCalories());
            if (m.getDescription().equals("")) {
                meals += "\nNo Description";
            } else {
                meals += ("\n" + m.getDescription());
            }
            meals += "\n";
        }
        return meals;
    }

    //EFFECTS: returns a formatted list of names, calories, and desc (if it has one) of all Meals eaten today
    // based on order of entry
    public String exerciseAllDetails() {
        String exercises = "";
        for (Exercise e:listOfE) {
            exercises += (e.getName() + ": " + e.getCalories());
            if (e.getDescription().equals("")) {
                exercises += "\nNo Description";
            } else {
                exercises += ("\n" + e.getDescription());
            }
            exercises += "\n";
        }
        return exercises;
    }

    public Calendar getDate() {
        return this.date;
    }

    public ArrayList<Exercise> getListOfE() {
        return listOfE;
    }

    public ArrayList<Meal> getListOfM() {
        return listOfM;
    }

}
