package ui;

import model.Day;
import model.Meal;
import org.json.JSONObject;
import persistence.JsonWriter;
import persistence.Writable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

//Main method, runs CaloriEx app
public class Main {
    private static JTextField text;
    private static JTextField text1;
    static JFrame frame;
    static JPanel tester;

    private static JButton countButton(String title) {
        class CountListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                String get = text.getText();
                if (get.equals("hello")) {
                    text.setVisible(false);

                    text1 = new JTextField(10);
                    text1.setText("fuuuuuck you");
                    tester.add(text1);
                    frame.revalidate();
                    frame.repaint();
                }

            }
        }

        JButton button = new JButton(title);
        button.addActionListener(new CountListener());
        return button;
    }

    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(2000,12,25);
//        SimpleDateFormat ft = new SimpleDateFormat("E dd-MM-yyyy");
//        //System.out.println(ft.format(cal.getTime()));
//        Scanner input = new Scanner(System.in).useDelimiter("\n");
//        String text = "";
//        System.out.println("print");
//        System.out.println(input.next().equals("Y"));


//        String mdesc1 = "Big Mac, 10 piece Chicken Nuggets, with Fries and soda";
//        String mdesc2 = "California Roll, Dynamite Roll, with Salmon Sashimi";
//        String edesc1 = "100 Push ups, 100 Sit ups, 100 Squats and 10Km run";
//        String edesc2 = "1km free-style, 1km breast-stroke";
//        Day day = new Day();
//
//        day.addExercise("Swimming", edesc2, 650);
//        day.addExercise("Gym", edesc1, 450);
//        day.addExercise("Grouse Grind", 350);
//        day.addMeal("Sushi", mdesc2, 550);
//        day.addMeal("McDonalds", mdesc1, 725);
//        day.addMeal("Cereal with Milk", 275);
//
//        JSONObject json = day.toJson();
//
//        JsonWriter write = new JsonWriter("./data/2020-10-29.json");
//        try {
//            write.open();
//            write.write(day);
//            write.close();
//        } catch (IOException e) {
//            return;
//        }
        //new CaloriEx();

        final int FRAME_WIDTH = 400;
        final int FRAME_HEIGHT = 300;
        frame = new JFrame();
        frame.setTitle("whats up fuuckers");
        //frame.add(new StartPanel());
        frame.add(new MainPanel());

//        tester = new JPanel();
//        text = new JTextField(10);
//        text.setText("hello");
//        text1 = new JTextField(10);
//        text1.setText("fuuuuuck you");
//        tester.add(text);
//        frame.add(tester);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
//        JButton button = countButton("wasssup");
//        JPanel bottom = new JPanel();
//        bottom.add(button);
//        frame.add(bottom, BorderLayout.SOUTH);



    }
}
