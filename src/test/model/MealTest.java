package model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MealTest {
    private Meal test;

    @Test
    public void testNoDescriptionConstructor() {
        test = new Meal("Pizza",350);
        assertEquals("Pizza", test.getName());
        assertEquals(350, test.getCalories());
        assertEquals("", test.getDescription());
    }

    @Test
    public void testConstructorWithDescription() {
        String desc ="California Roll, Dynamite Roll, with Salmon Sashimi";
        test = new Meal("Sushi",desc, 350);
        assertEquals("Sushi", test.getName());
        assertEquals(350, test.getCalories());
        assertEquals(desc, test.getDescription());
    }

    @Test
    public void testToJson() {
        String desc ="California Roll, Dynamite Roll, with Salmon Sashimi";
        test = new Meal("Sushi",desc, 350);
        JSONObject json = test.toJson();
        assertEquals(test.getName(), json.getString("Name"));
        assertEquals(test.getDescription(), json.getString("Description"));
        assertEquals(test.getCalories(), json.getInt("Calories"));
    }

}