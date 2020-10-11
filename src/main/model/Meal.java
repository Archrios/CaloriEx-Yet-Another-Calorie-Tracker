package model;

public class Meal {
    private String name;
    private String description;
    private int calories;

    public Meal(String name, String description, int calories) {
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    public Meal(String name, int calories) {
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
