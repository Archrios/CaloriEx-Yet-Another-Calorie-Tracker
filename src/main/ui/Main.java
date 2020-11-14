package ui;

import javax.swing.*;

//Main method, runs CaloriEx app
public class Main {
    static JFrame frame;

    public static void main(String[] args) {
//

        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 400;
        frame = new JFrame();
        frame.setTitle("CaloriEx");
        frame.add(new ControlPanel());

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
