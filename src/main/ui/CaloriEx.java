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
        boolean dayAdded = false;
        String userInput;
        int user = 0;
        input = new Scanner(System.in);

        do {
            startMenu();
            try {
                userInput = input.next();
                user = Integer.parseInt(userInput);
                dayAdded = startOperations(user);
            } catch (NumberFormatException e) {
                System.out.println("Invalid user input, please try again");
            }
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
            System.out.println("Enter the date you would like to add in the following format:\nYYYY-MM-DD");
            try {
                String inputs = input.next();
                String[] dayFields = inputs.split("-");
                year = Integer.parseInt(dayFields[0]);
                month = Integer.parseInt(dayFields[1]);
                date = Integer.parseInt(dayFields[2]);
                day = new Day(year, month, date);
            } catch (Exception e) {
                return printAndReturn(e.toString() + " please try again", false);
            }
        } else {
            return printAndReturn("User input not recognized, please try again",false);
        }
        setJsonLocation(day.getDate().toString());
        return true;
    }

    //MODIFIES: this
    //EFFECTS: sets jsonLocation and builds new Writer and Reader based on that
    //Mainly to circumvent 25 line limit
    private void setJsonLocation(String date) {
        jsonLocation += (day.getDate().toString() + ".json");
        jsonWriter = new JsonWriter(jsonLocation);
        jsonReader = new JsonReader(jsonLocation);
    }

    //EFFECTS: Displays the start menu
    private void startMenu() {
        System.out.println("\nWould you like to add a new day or load a previous day?");
        System.out.println("1 - New Day");
        System.out.println("2 - Load a previous date");
    }


    //MODIFIES: this
    //EFFECTS: adds new day or loads one based on user inputs
    private boolean startOperations(int user) {
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

    //MODIFIES: this
    //EFFECTS: attempts to load a day from a file
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
            System.out.println("File could not be found, please try again");
            resetLocation();
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: loads a day based on user inputs
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
        jsonLocation += (date + ".json");
        jsonWriter = new JsonWriter(jsonLocation);
        jsonReader = new JsonReader(jsonLocation);
        return true;
    }

    //EFFECTS: displays a menu of options for type of day to load
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
        try {
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
        } catch (NumberFormatException e) {
            return printAndReturn(e.toString() + "please try again", true);
        }
    }

    //EFFECTS: Prints out a given message and returns given boolean
    // Mainly to circumvent the 25 line limit
    private boolean printAndReturn(String message, boolean bool) {
        System.out.println(message);
        return bool;
    }

    //MODIFIES: this
    //EFFECTS: writes save file to desginated location
    private void saveDay() {
        try {
            jsonWriter.open();
            jsonWriter.write(day);
            jsonWriter.close();
            System.out.println("File saved to: " + jsonLocation);
        } catch (FileNotFoundException e) {
            System.out.println("File not saved to: " + jsonLocation);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds exercise to day using on user inputs
    private boolean addExercise() {
        input = new Scanner(System.in).useDelimiter("\n");
        String name;
        String desc;
        int cals;
        System.out.println("\nWould you like to add a description to this exercise? Y/N");
        boolean hasDesc = (input.next().equals("Y"));
        System.out.println("Please enter the name of the exercise.");
        name = input.next();
        System.out.println("Please enter the calories burned by the exercise.");
        try {
            cals = Integer.parseInt(input.next());
        } catch (NumberFormatException e) {
            return printAndReturn(e.toString() + "please try again", false);
        }
        if (hasDesc) {
            System.out.println("Please enter the description of the exercise.");
            desc = input.next();
            day.addExercise(name, desc, cals);
        } else {
            day.addExercise(name, cals);
        }
        input = new Scanner(System.in);
        return printAndReturn("The exercise: \"" + name + "\" has been added successfully", true);
    }

    //MODIFIES: this
    //EFFECTS: adds meal to day using user input
    private boolean addMeal() {
        input = new Scanner(System.in).useDelimiter("\n");
        String name;
        String desc;
        int cals;
        System.out.println("\nWould you like to add a description to this meal? Y/N");
        boolean hasDesc = (input.next().equals("Y"));
        System.out.println("Please enter the name of the meal.");
        name = input.next();
        System.out.println("Please enter the calories in the meal.");
        try {
            cals = Integer.parseInt(input.next());
        } catch (NumberFormatException e) {
            return printAndReturn(e.toString() + "please try again", false);
        }
        if (hasDesc) {
            System.out.println("Please enter the description of the meal.");
            desc = input.next();
            day.addMeal(name, desc, cals);
        } else {
            day.addMeal(name, cals);
        }
        input = new Scanner(System.in);
        return printAndReturn("The meal: \"" + name + "\" has been added successfully", true);

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
        boolean added = false;
        try {
            int user = Integer.parseInt(input.next());
            switch (user) {
                case 1:
                    do {
                        added = addMeal();
                    } while (!added);
                    break;
                case 2:
                    do {
                        added = addExercise();
                    } while (!added);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid user input, please try again");
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
        try {
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
        } catch (NumberFormatException e) {
            //return errorCaught(e.toString() + "please try again", true);
            System.out.println("Invalid user input, please try again");
        }
    }

    //MODIFIES: this
    // resets the read/write location jsonLocation to default
    private void resetLocation() {
        jsonLocation = "./data/";
    }
}
