package persistence;

import model.Day;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// modeled after the class of the same name in repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest {
    private Day test;
    String mdesc1 = "Big Mac, 10 piece Chicken Nuggets, with Fries and soda";
    String mdesc2 = "California Roll, Dynamite Roll, with Salmon Sashimi";
    String edesc1 = "100 Push ups, 100 Sit ups, 100 Squats and 10Km run";
    String edesc2 = "1km free-style, 1km breast-stroke";

    @Test
    void testWriterInvalidFile() {
        try {
            test = new Day(2020,10,20);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDay() {

        try {
            test = new Day(2020,10,20);
            JsonWriter writer = new JsonWriter("./data/2020-10-20.json");
            writer.open();
            writer.write(test);
            writer.close();


            JsonReader reader = new JsonReader("./data/2020-10-20.json");
            test = reader.read();
            assertEquals("2020-10-20", test.getDate().toString());
            assertEquals(0, test.getMeals().size());
            assertEquals(0, test.getExercises().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testWriterDay() {
        JsonReader reader = new JsonReader("./data/2020-10-29.json");
        try {
            test = new Day(2020,10,20);
            test.addExercise("Swimming", edesc2, 650);
            test.addExercise("Gym", edesc1, 450);
            test.addExercise("Grouse Grind", 350);
            test.addMeal("Sushi", mdesc2, 550);
            test.addMeal("McDonalds", mdesc1, 725);
            test.addMeal("Cereal with Milk", 275);
            JsonWriter writer = new JsonWriter("./data/2020-10-20.json");
            writer.open();
            writer.write(test);
            writer.close();

            test = reader.read();
            assertEquals("2020-10-29", test.getDate().toString());
            assertEquals(3, test.getMeals().size());
            String m1 = "Sushi: 550\n"+mdesc2+"\n";
            String m2 = "McDonalds: 725\n"+mdesc1+"\n";
            String m3 = "Cereal with Milk: 275\nNo Description\n";
            assertEquals(m1+"\n"+m2+"\n"+m3+"\n", test.mealAllDetails());

            assertEquals(3, test.getExercises().size());
            String e1 = "Swimming: 650\n"+edesc2+"\n";
            String e2 = "Gym: 450\n"+edesc1+"\n";
            String e3 = "Grouse Grind: 350\nNo Description\n";
            assertEquals(e1+"\n"+e2+"\n"+e3+"\n", test.exerciseAllDetails());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
