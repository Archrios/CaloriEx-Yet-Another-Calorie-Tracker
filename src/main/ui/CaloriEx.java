package ui;

import model.Day;

import java.util.Scanner;

public class CaloriEx {
    private Scanner input;
    //private ArrayList<Day> record = new ArrayList<Day>();
    private Day day;

    public CaloriEx() {
        runCaloriEx();
    }

    private void runCaloriEx() {
        boolean running;
        boolean dayAdded;
        String user;
        input = new Scanner(System.in);
        startMenu();
        do {
            user = input.next();
            dayAdded = addDay(user);
        } while (!dayAdded);
        do {
            running = dayOperations();
        } while (running);
        System.out.println("Thanks for using CaloriEx, hope you enjoyed it!!");
    }

    private boolean addDay(String user) {
        if (user.equals("t")) {
            //record.add(new Day());
            System.out.println("you have chosen today");
            day = new Day();
            return true;
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
            return true;
        } else {
            System.out.println("User input not recognized, please try again");
            return false;
        }
    }

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
            case 0:
                return false;
        }
        return true;
    }

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

    private void startMenu() {
        System.out.println("\nWhich day would you like to add to?");
        System.out.println("t - Today");
        System.out.println("s - Select Own date");
    }

    private void mainMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1: Add a meal/exercise");
        System.out.println("2: Check calorie summary for this date");
        System.out.println("3: Check details about meals and/or exercises done this date");
        System.out.println("0: Exit application");
    }

    private void caloricSummary() {
        System.out.println("\nCalories in: " + day.caloriesIn());
        System.out.println("Calories out: " + day.caloriesOut());
        System.out.println("Calories total: " + day.caloricTotal());
    }

    private void additionMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1: Add a meal");
        System.out.println("2: Add an exercise");
    }

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

    private void recordMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1: List of meals eaten this date");
        System.out.println("2: Details of meals eaten this date");
        System.out.println("3: List of exercises done this date");
        System.out.println("4: Details of exercises done this date");
    }

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
}
