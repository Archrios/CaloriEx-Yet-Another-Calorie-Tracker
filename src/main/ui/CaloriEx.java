package ui;

import model.Day;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

// Calorie counter application
public class CaloriEx {
    private Scanner input;
    //private ArrayList<Day> record = new ArrayList<Day>();
    private String jsonLocation = "./data/";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Day day;

    //EFFECTS: Runs the CaloriEx application
    public CaloriEx() {
        runCaloriEx();
    }

    //MODIFIES: this
    //EFFECTS: takes user input which is used by addDay to modify the Day; if user input isnt appropriate, loops it
    // until user input qualifies
    // main process is then run in a loop
    private void runCaloriEx() {
        boolean running;
        boolean dayAdded;
        String user;
        input = new Scanner(System.in);
        //dateMenu();
        do {
            startMenu();
            dayAdded = startOperations();
        } while (!dayAdded);
        do {
            running = dayOperations();
        } while (running);
        System.out.println("Thanks for using CaloriEx, hope you enjoyed it!!");
    }

    //MODIFIES: this
    //EFFECTS: modifies Day based on user inputs
    private boolean addDay(String user) {
        if (user.equals("t")) {
            //record.add(new Day());
            System.out.println("you have chosen today");
            day = new Day();
        } else if (user.equals("s")) {
            int year;
            int month;
            int date;
            System.out.println("Enter the date you would like to add in the following format:");
            System.out.println("YYYY MM DD");
            year = Integer.parseInt(input.next());
            month = Integer.parseInt(input.next());
            date = Integer.parseInt(input.next());
            //record.add(new Day(year, month, day));
            day = new Day(year, month, date);
        } else {
            System.out.println("User input not recognized, please try again");
            return false;
        }
        jsonLocation += (day.getDate().toString() + ".json");
        jsonWriter = new JsonWriter(jsonLocation);
        jsonReader = new JsonReader(jsonLocation);
        return true;
    }

    private void startMenu() {
        System.out.println("\nWould you like to add a new day or load a previous day?");
        System.out.println("1 - New Day");
        System.out.println("2 - Load a previous date");
    }

    private boolean startOperations() {
        int user = input.nextInt();
        switch (user) {
            case 1:
                dateMenu();
                String choice = input.next();
                return addDay(choice);
            case 2:
                return loadDay();
            default:
                System.out.println("User input not recognized");
                return false;
        }
    }

    private boolean loadDay() {

        boolean dateAdded = false;
        do {
            loadMenu();
            String user = input.next();
            dateAdded = loadOperations(user);
        } while (!dateAdded);
        try {
            day = jsonReader.read();
            return true;
        } catch (IOException e) {
            System.out.println("ISSA PROBLEMO HOMBRE");
            resetLocation();
            return false;
        }
    }

    private boolean loadOperations(String user) {
        String date;
        if (user.equals("t")) {
            //record.add(new Day());
            System.out.println("you have chosen today");
            date = LocalDate.now().toString();
        } else if (user.equals("s")) {
            System.out.println("Enter the date you would like to add in the following format including the dashes:");
            System.out.println("YYYY-MM-DD");
            date = input.next();
        } else {
            System.out.println("User input not recognized, please try again");
            return false;
        }
        System.out.println("i want to fucking load: " + date);
        jsonLocation += (date + ".json");
        System.out.println("save location is: " + jsonLocation);
        jsonWriter = new JsonWriter(jsonLocation);
        jsonReader = new JsonReader(jsonLocation);
        return true;
    }

    //EFFECTS: displays a menu of options for type of day to add
    private void loadMenu() {
        System.out.println("\nWhich day would you like to load from?");
        System.out.println("t - Today");
        System.out.println("s - Select Own date");
    }

    //EFFECTS: displays a menu of options for type of day to add
    private void dateMenu() {
        System.out.println("\nWhich day would you like to add to?");
        System.out.println("t - Today");
        System.out.println("s - Select Own date");
    }

    //MODIFIES: this
    //EFFECTS: displays main menu, and calls appropriate functions according to user input
    private boolean dayOperations() {
        mainMenu();
        int user = Integer.parseInt(input.next());
        switch (user) {
            case 1:
                addOperations();
                break;
            case 2:
                caloricSummary();
                break;
            case 3:
                recordOperations();
                break;
            case 4:
                saveDay();
                break;
            case 0:
                return false;
        }
        return true;
    }

    private void saveDay() {
        try {
            jsonWriter.open();
            jsonWriter.write(day);
            jsonWriter.close();
            System.out.println("saveeeeddddd");
        } catch (FileNotFoundException e) {
            System.out.println("nooot saved :(");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds exercise to day using on user inputs
    private void addExercise() {
        input = new Scanner(System.in).useDelimiter("\n");
        String name;
        String desc;
        int cals;
        System.out.println("\nWould you like to add a description to this exercise? Y/N");
        boolean hasDesc = (input.next().equals("Y"));
        System.out.println("Please enter the name of the exercise.");
        name = input.next();
        System.out.println("Please enter the calories burned by the exercise.");
        cals = Integer.parseInt(input.next());
        if (hasDesc) {
            System.out.println("Please enter the description of the exercise.");
            desc = input.next();
            day.addExercise(name, desc, cals);
        } else {
            day.addExercise(name, cals);
        }
        System.out.println("The exercise: \"" + name + "\" has been added successfully");
        input = new Scanner(System.in);
    }

    //MODIFIES: this
    //EFFECTS: adds meal to day using user input
    private void addMeal() {
        input = new Scanner(System.in).useDelimiter("\n");
        String name;
        String desc;
        int cals;
        System.out.println("\nWould you like to add a description to this meal? Y/N");
        boolean hasDesc = (input.next().equals("Y"));
        System.out.println("Please enter the name of the meal.");
        name = input.next();
        System.out.println("Please enter the calories in the meal.");
        cals = Integer.parseInt(input.next());
        if (hasDesc) {
            System.out.println("Please enter the description of the meal.");
            desc = input.next();
            day.addMeal(name, desc, cals);
        } else {
            day.addMeal(name, cals);
        }
        System.out.println("The meal: \"" + name + "\" has been added successfully");
        input = new Scanner(System.in);
    }

    //EFFECTS: displays a menu of options to user
    private void mainMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1: Add a meal/exercise");
        System.out.println("2: Check calorie summary for this date");
        System.out.println("3: Check details about meals and/or exercises done this date");
        System.out.println("4: Save records");
        System.out.println("0: Exit application");
    }

    //EFFECTS: displays summary of the day's calorie totals using Day methods
    private void caloricSummary() {
        System.out.println("\nCalories in: " + day.caloriesIn());
        System.out.println("Calories out: " + day.caloriesOut());
        System.out.println("Calories total: " + day.caloricTotal());
    }

    //EFFECTS: displays a menu of options to user
    private void additionMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1: Add a meal");
        System.out.println("2: Add an exercise");
    }

    //MODIFIES: this
    //EFFECTS: calls methods to add meal or exercise based on user inputs
    private void addOperations() {
        additionMenu();
        int user = Integer.parseInt(input.next());
        switch (user) {
            case 1:
                addMeal();
                break;
            case 2:
                addExercise();
                break;
        }
    }

    //EFFECTS: displays a menu of options to user
    private void recordMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1: List of meals eaten this date");
        System.out.println("2: Details of meals eaten this date");
        System.out.println("3: List of exercises done this date");
        System.out.println("4: Details of exercises done this date");
    }

    //EFFECTS: displays meal/exercise information based on user inputs
    private void recordOperations() {
        recordMenu();
        int user = Integer.parseInt(input.next());
        switch (user) {
            case 1:
                System.out.println("\n" + day.mealNames());
                break;
            case 2:
                System.out.println("\n" + day.mealAllDetails());
                break;
            case 3:
                System.out.println("\n" + day.exerciseNames());
                break;
            case 4:
                System.out.println("\n" + day.exerciseAllDetails());
                break;
        }
    }

    private void resetLocation() {
        jsonLocation = "./data/";
    }
}
