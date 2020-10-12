package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Day test;
    private Calendar cal;
    SimpleDateFormat df = new SimpleDateFormat("E dd-MM-yyyy");
    String mdesc1 = "Big Mac, 10 piece Chicken Nuggets, with Fries and soda";
    String mdesc2 ="California Roll, Dynamite Roll, with Salmon Sashimi";
    String edesc1 ="100 Push ups, 100 Sit ups, 100 Squats and 10Km run";
    String edesc2 ="1km free-style, 1km breast-stroke";

    @BeforeEach
    public void runBefore() {
        test = new Day();
        cal = Calendar.getInstance();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, test.getListOfE().size());
        assertEquals(0, test.getListOfM().size());
        assertEquals(cal, test.getDate());
    }

    @Test
    public void testConstructor() {
        test = new Day(2020, 10, 31);
        cal.set(2020, 10 - 1, 31);
        assertEquals(0, test.getListOfE().size());
        assertEquals(0, test.getListOfM().size());
        assertEquals(df.format(cal.getTime()), df.format(test.getDate().getTime()));
    }

    @Test
    public void testAddMealWithDescription() {
        test.addMeal("McDonalds", mdesc1, 725);
        assertEquals(1, test.getListOfM().size());
        assertEquals("McDonalds", test.getListOfM().get(0).getName());
        assertEquals(mdesc1, test.getListOfM().get(0).getDescription());
        assertEquals(725, test.getListOfM().get(0).getCalories());
    }

    @Test
    public void testAddMealWithNoDescription() {
        test.addMeal("McDonalds", 725);
        assertEquals(1, test.getListOfM().size());
        assertEquals("McDonalds", test.getListOfM().get(0).getName());
        assertEquals("", test.getListOfM().get(0).getDescription());
        assertEquals(725, test.getListOfM().get(0).getCalories());
    }

    @Test
    public void testAddMultipleMealsWithAndWithoutDescription() {
        test.addMeal("Sushi", mdesc2, 550);
        test.addMeal("McDonalds", mdesc1, 725);
        test.addMeal("Cereal with Milk", 275);
        assertEquals(3, test.getListOfM().size());
        assertEquals("McDonalds", test.getListOfM().get(1).getName());
        assertEquals(mdesc2, test.getListOfM().get(0).getDescription());
        assertEquals(mdesc1, test.getListOfM().get(1).getDescription());
        assertEquals(275, test.getListOfM().get(2).getCalories());
    }

    @Test
    public void testAddExerciseWithDescription() {
        test.addExercise("Gym", edesc1, 450);
        assertEquals(1,test.getListOfE().size());
        assertEquals("Gym", test.getListOfE().get(0).getName());
        assertEquals(450, test.getListOfE().get(0).getCalories());
        assertEquals(edesc1, test.getListOfE().get(0).getDescription());
    }

    @Test
    public void testAddExerciseWithNoDescription() {
        test.addExercise("Grouse Grind", 350);
        assertEquals(1,test.getListOfE().size());
        assertEquals("Grouse Grind", test.getListOfE().get(0).getName());
        assertEquals(350, test.getListOfE().get(0).getCalories());
        assertEquals("", test.getListOfE().get(0).getDescription());
    }

    @Test
    public void testAddManyExerciseWithAndWithoutDescription() {
        test.addExercise("Gym", edesc1, 450);
        test.addExercise("Grouse Grind", 350);
        test.addExercise("Swimming", edesc2, 650);
        assertEquals(3,test.getListOfE().size());
        assertEquals("Gym", test.getListOfE().get(0).getName());
        assertEquals(350, test.getListOfE().get(1).getCalories());
        assertEquals(edesc2, test.getListOfE().get(2).getDescription());
    }

    @Test
    public void testCaloriesInWithEmptyListOfMeals() {
        assertEquals(0,test.caloriesIn());
    }

    @Test
    public void testCaloriesInWithOneMeal() {
        test.addMeal("Sushi", mdesc2, 550);
        assertEquals(550,test.caloriesIn());
    }

    @Test
    public void testCaloriesInWithMultipleMeals() {
        test.addMeal("Sushi", mdesc2, 550);
        test.addMeal("McDonalds", mdesc1, 725);
        test.addMeal("Cereal with Milk", 275);
        assertEquals(1550,test.caloriesIn());
    }

    @Test
    public void testCaloriesOutWithNoExercise() {
        assertEquals(0,test.caloriesOut());
    }

    @Test
    public void testCaloriesOutWithOneExercise() {
        test.addExercise("Gym", edesc1, 450);
        assertEquals(450,test.caloriesOut());
    }

    @Test
    public void testCaloriesOutWithMultipleExercises() {
        test.addExercise("Gym", edesc1, 450);
        test.addExercise("Grouse Grind", 350);
        test.addExercise("Swimming", edesc2, 650);
        assertEquals(1450,test.caloriesOut());
    }

    @Test
    public void testCaloricTotalHavingDoneNothing(){
        assertEquals(0,test.caloricTotal());
    }

    @Test
    public void testCaloricTotalHavingOnlyEatenMeals(){
        test.addMeal("Sushi", mdesc2, 550);
        test.addMeal("McDonalds", mdesc1, 725);
        test.addMeal("Cereal with Milk", 275);
        assertEquals(1550,test.caloricTotal());
    }

    @Test
    public void testCaloricTotalHavingOnlyExercised(){
        test.addExercise("Gym", edesc1, 450);
        test.addExercise("Grouse Grind", 350);
        test.addExercise("Swimming", edesc2, 650);
        assertEquals(-1450,test.caloricTotal());
    }

    @Test
    public void testCaloricTotalHavingExercisedAndEaten(){
        test.addMeal("Sushi", mdesc2, 550);
        test.addMeal("McDonalds", mdesc1, 725);
        test.addMeal("Cereal with Milk", 275);
        test.addExercise("Gym", edesc1, 450);
        test.addExercise("Grouse Grind", 350);
        test.addExercise("Swimming", edesc2, 650);
        assertEquals(100,test.caloricTotal());
    }

    @Test
    public void testExerciseNamesWithNoExercises() {
        assertEquals("",test.exerciseNames());
    }

    @Test
    public void testExerciseNamesWithOneExercise() {
        test.addExercise("Grouse Grind", 350);
        assertEquals("Grouse Grind\n",test.exerciseNames());
    }

    @Test
    public void testExerciseNamesWithMultipleExercise() {
        test.addExercise("Gym", edesc1, 450);
        test.addExercise("Grouse Grind", 350);
        test.addExercise("Swimming", edesc2, 650);
        assertEquals("Gym\nGrouse Grind\nSwimming\n",test.exerciseNames());
    }

    @Test
    public void testMealNamesWithNoMeals() {
        assertEquals("",test.mealNames());
    }

    @Test
    public void testMealNamesWithOneMeals() {
        test.addMeal("Sushi", mdesc2, 550);
        assertEquals("Sushi\n",test.mealNames());
    }

    @Test
    public void testMealNamesWithMultipleMeals() {
        test.addMeal("Sushi", mdesc2, 550);
        test.addMeal("McDonalds", mdesc1, 725);
        test.addMeal("Cereal with Milk", 275);
        assertEquals("Sushi\nMcDonalds\nCereal with Milk\n",test.mealNames());
    }







}
