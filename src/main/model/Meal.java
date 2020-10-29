package model;

import org.json.JSONObject;

//Represents a Meal having a name, number of calories contained
// and an optional description of the meal
// inherits fields and base methods from Activity class
public class Meal extends Activity {

    // REQUIRES: Calories > 0
    // EFFECTS: Meal is constructed with a given name, description and number of calories eaten
    public Meal(String name, String description, int calories) {
        super(name, description, calories);
    }

    // REQUIRES: Calories > 0
    // EFFECTS: Meal is constructed with a given name, and number of calories eaten
    public Meal(String name, int calories) {
        super(name, calories);
    }

    //EFFECTS: Returns all details of the Exercise as a formatted string
    public String toString() {
        return super.toString();
    }

    //EFFECTS: returns Exercise as a json object using super method
    //modeled after the repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        return super.toJson();
    }
}
