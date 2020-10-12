package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {
    private Exercise test;

    @Test
    public void testNoDescriptionConstructor() {
        test = new Exercise("Badminton",200);
        assertEquals("Badminton", test.getName());
        assertEquals(200, test.getCalories());
        assertEquals("", test.getDescription());
    }

    @Test
    public void testConstructorWithDescription() {
        String desc ="100 Push ups, 100 Sit ups, 100 Squats and 10Km run";
        test = new Exercise("Gym",desc, 450);
        assertEquals("Gym", test.getName());
        assertEquals(450, test.getCalories());
        assertEquals(desc, test.getDescription());
    }

}
