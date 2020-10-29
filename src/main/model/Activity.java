package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents an Activity having a name, number of calories associated with it
// and an optional description of the Activity
public abstract class Activity implements Writable {
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

    public String toString() {
        StringBuilder activity = new StringBuilder();
        activity.append(name + ": " + calories);
        if (description.equals("")) {
            activity.append("\nNo Description" + "\n");
        } else {
            activity.append("\n" + description + "\n");
        }
        return activity.toString();
    }

    //EFFECTS: returns this as a JSON Object
    //modeled after the repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Description", description);
        json.put("Calories", calories);
        return json;
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
