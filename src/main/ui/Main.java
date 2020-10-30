package ui;

import model.Day;
import model.Meal;
import org.json.JSONObject;
import persistence.JsonWriter;
import persistence.Writable;

import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(2000,12,25);
//        SimpleDateFormat ft = new SimpleDateFormat("E dd-MM-yyyy");
//        //System.out.println(ft.format(cal.getTime()));
//        Scanner input = new Scanner(System.in).useDelimiter("\n");
//        String text = "";
//        System.out.println("print");
//        System.out.println(input.next().equals("Y"));


        String mdesc1 = "Big Mac, 10 piece Chicken Nuggets, with Fries and soda";
        String mdesc2 = "California Roll, Dynamite Roll, with Salmon Sashimi";
        String edesc1 = "100 Push ups, 100 Sit ups, 100 Squats and 10Km run";
        String edesc2 = "1km free-style, 1km breast-stroke";
        Day day = new Day();

        day.addExercise("Swimming", edesc2, 650);
        day.addExercise("Gym", edesc1, 450);
        day.addExercise("Grouse Grind", 350);
        day.addMeal("Sushi", mdesc2, 550);
        day.addMeal("McDonalds", mdesc1, 725);
        day.addMeal("Cereal with Milk", 275);

        JSONObject json = day.toJson();

        JsonWriter write = new JsonWriter("./data/2020-10-29.json");
        try {
            write.open();
            write.write(day);
            write.close();
        } catch (IOException e) {
            return;
        }
        //new CaloriEx();

    }
}
