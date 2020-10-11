package model;

public class Exercise {
    private String name;
    private String description;
    private int calories;

    public Exercise(String name, String description, int calories) {
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    public Exercise(String name, int calories) {
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
