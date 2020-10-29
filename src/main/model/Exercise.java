package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Exercise having a name, number of calories burned
// and an optional description of the exercise
public class Exercise extends Activity {

    // REQUIRES: Calories > 0
    // EFFECTS: Exercise is constructed with a given name, description and number of calories burned
    public Exercise(String name, String description, int calories) {
        super(name, description, calories);
    }

    // REQUIRES: Calories > 0
    // EFFECTS: Exercise is constructed with a given name and number of calories burned
    public Exercise(String name, int calories) {
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
