package model;

//Represents an Activity having a name, number of calories associated with it
// and an optional description of the Activity
public abstract class Activity {
    private String name;
    private String description;
    private int calories;

    // REQUIRES: Calories > 0
    // EFFECTS: Activity is constructed with a given name, description and number of calories associated with it
    public Activity(String name, String description, int calories) {
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    // REQUIRES: Calories > 0
    // EFFECTS: Meal is constructed with a given name, description and number of calories associated with it
    public Activity(String name, int calories) {
        this.name = name;
        this.description = "";
        this.calories = calories;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCalories() {
        return this.calories;
    }

}
