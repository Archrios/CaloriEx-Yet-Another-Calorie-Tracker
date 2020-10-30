package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;

//Represents a day which contains a list of meals eaten and a list of exercises done
public class Day implements Writable {
    private ArrayList<Meal> meals;
    private ArrayList<Exercise> exercises;
    //private Calendar date = Calendar.getInstance();
    private LocalDate date;

    // EFFECTS: Constructs a day with empty list of exercises and meals, with the date as the current date
    public Day() {
        exercises = new ArrayList<Exercise>();
        meals = new ArrayList<Meal>();
        date = LocalDate.now();
    }

    //REQUIRES: MONTH is between 1 and 12, day is between 1 and 31, and actually fits according to the month
    // nothing like February the 30th
    // EFFECTS: Constructs a day with empty list of exercises and meals, with the date based on user input
    public Day(int year, int month, int day) {
        exercises = new ArrayList<Exercise>();
        meals = new ArrayList<Meal>();
//        //Java is weird and starts months with 0; Jan is 0, and Dec is 11 so its easier to do so here
//        //than telling user to manually offset
//        date.set(year, month - 1, day);

        date = LocalDate.of(year, month, day);
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: adds a Meal to the list of Meals with a name, description, and number of calories
    public void addMeal(String name, String description, int calories) {
        meals.add(new Meal(name, description, calories));
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: adds a Meal to the list of Meals with a name, and number of calories
    public void addMeal(String name, int calories) {
        meals.add(new Meal(name, calories));
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: adds an Exercise to the list of Exercise with a name, description, and calories burned
    public void addExercise(String name, String description, int calories) {
        exercises.add(new Exercise(name, description, calories));
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: adds an Exercise to the list of Exercise with a name, and calories burned
    public void addExercise(String name, int calories) {
        exercises.add(new Exercise(name, calories));
    }

    // EFFECTS: returns the total number of calories consumed today
    public int caloriesIn() {
        int total = 0;
        for (Meal m: meals) {
            total += m.getCalories();
        }
        return total;
    }

    // EFFECTS: returns the total number of calories burned today
    public int caloriesOut() {
        int total = 0;
        for (Exercise e: exercises) {
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
        for (Exercise e: this.exercises) {
            exercises += e.getName();
            exercises += "\n";
        }
        return exercises;
    }

    // EFFECTS: returns a formatted list of the names of all the Meals eaten today based on order of entry
    public String mealNames() {
        String meals = "";
        for (Meal m: this.meals) {
            meals += m.getName();
            meals += "\n";
        }
        return meals;
    }

    //EFFECTS: returns a formatted list of names, calories, and desc (if it has one) of all Meals eaten today
    // based on order of entry
    public String mealAllDetails() {
        StringBuilder list = new StringBuilder();
        for (Meal m: meals) {
            list.append(m.toString() + "\n");
        }
        return list.toString();
    }

    //EFFECTS: returns a formatted list of names, calories, and desc (if it has one) of all Meals eaten today
    // based on order of entry
    public String exerciseAllDetails() {
        StringBuilder list = new StringBuilder();
        for (Exercise e: exercises) {
            list.append(e.toString() + "\n");
        }
        return list.toString();
    }

    //EFFECTS: returns this as a JSON Object
    //modeled after the repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Date", date.toString());
        json.put("Meals", mealToJson());
        json.put("Exercises", exerciseToJson());
        return json;
    }

    //EFFECTS: returns the list of meals as a JSON ARRAY
    //modeled after the repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONArray mealToJson() {
        JSONArray array = new JSONArray();
        for (Meal m: meals) {
            array.put(m.toJson());
        }
        return array;
    }

    //EFFECTS: returns the list of exercises as a JSON ARRAY
    //modeled after the repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONArray exerciseToJson() {
        JSONArray array = new JSONArray();
        for (Exercise e: exercises) {
            array.put(e.toJson());
        }
        return array;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

}
