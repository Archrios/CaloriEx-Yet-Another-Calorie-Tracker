package model;

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

}