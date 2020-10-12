package model;

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
}
