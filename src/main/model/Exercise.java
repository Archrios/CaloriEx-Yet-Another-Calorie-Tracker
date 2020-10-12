package model;

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
}
