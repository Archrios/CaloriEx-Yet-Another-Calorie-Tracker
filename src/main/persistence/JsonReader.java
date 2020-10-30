package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Day;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// modeled after the class of the same name in repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Day read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDay(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses Day from JSON object and returns it
    private Day parseDay(JSONObject jsonObject) {
        Day day = addDay(jsonObject);
        addMeals(day, jsonObject);
        addExercises(day, jsonObject);
        return day;
    }

    // EFFECTS: parses date from JSON object and returns it
    private Day addDay(JSONObject jsonObject) {
        int year;
        int month;
        int date;
        String dateString = jsonObject.getString("Date");
        String[] dayFields = dateString.split("-");
        year = Integer.parseInt(dayFields[0]);
        month = Integer.parseInt(dayFields[1]);
        date = Integer.parseInt(dayFields[2]);
        Day day = new Day(year, month, date);
        return day;
    }

    // MODIFIES: day
    // EFFECTS: parses meals from JSON object and adds them to day
    private void addMeals(Day day, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(day, nextMeal);
        }
    }

    // MODIFIES: day
    // EFFECTS: parses meal from JSON object and adds it to day
    private void addMeal(Day day, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        String desc = jsonObject.getString("Description");
        int calories = jsonObject.getInt("Calories");
        day.addMeal(name, desc, calories);
    }

    // MODIFIES: day
    // EFFECTS: parses exercises from JSON object and adds them to day
    private void addExercises(Day day, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(day, nextExercise);
        }
    }

    // MODIFIES: day
    // EFFECTS: parses exercise from JSON object and adds it to day
    private void addExercise(Day day, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        String desc = jsonObject.getString("Description");
        int calories = jsonObject.getInt("Calories");
        day.addExercise(name, desc, calories);
    }
}

