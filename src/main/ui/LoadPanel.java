package ui;

import model.Day;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

//public class LoadPanel extends JPanel {
//    private JLabel label;
//    private JButton todayButton;
//    private JButton selectButton;
//    private MainPanel mainPanel;
//
////    public DatePanel() {
////        label = new JLabel("Which day would you like to load from?");
////        todayButton = createTodayButton("Today");
////        selectButton = createSelectButton("Select own date");
////        todayButton.setAlignmentX(CENTER_ALIGNMENT);
////        label.setAlignmentX(CENTER_ALIGNMENT);
////        selectButton.setAlignmentX(CENTER_ALIGNMENT);
////        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
////        this.add(Box.createRigidArea(new Dimension(0,20)));
////        this.add(label);
////        this.add(Box.createRigidArea(new Dimension(0,50)));
////        this.add(todayButton);
////        this.add(Box.createRigidArea(new Dimension(0,20)));
////        this.add(selectButton);
////    }
//
//    public LoadPanel(MainPanel main) {
//        mainPanel = main;
//        label = new JLabel("Which day would you like to load from?");
//        todayButton = createTodayButton("Today");
//        selectButton = createSelectButton("Select own date");
//        todayButton.setAlignmentX(CENTER_ALIGNMENT);
//        label.setAlignmentX(CENTER_ALIGNMENT);
//        selectButton.setAlignmentX(CENTER_ALIGNMENT);
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.add(Box.createRigidArea(new Dimension(0,20)));
//        this.add(label);
//        this.add(Box.createRigidArea(new Dimension(0,50)));
//        this.add(todayButton);
//        this.add(Box.createRigidArea(new Dimension(0,20)));
//        this.add(selectButton);
//    }
//
//    private JButton createTodayButton(String title) {
//        class NewListener implements ActionListener {
//            public void actionPerformed(ActionEvent event) {
////                String get = text.getText();
////                if (get.equals("hello")) {
////                    text.setVisible(false);
////
////                    text1 = new JTextField(10);
////                    text1.setText("fuuuuuck you");
////                    tester.add(text1);
////                    frame.revalidate();
////                    frame.repaint();
////                }
//                String date = LocalDate.now().toString();
//                mainPanel.setJsonLocation(date);
//                try {
//                    mainPanel.setDay(mainPanel.getJsonReader().read());
//                    JOptionPane.showMessageDialog(null, "File loaded from: " + date);
//                    setVisible(false);
//                } catch (IOException e) {
//                    JOptionPane.showMessageDialog(null, "File could not be found, please try again");
//                    mainPanel.resetLocation();
//                }
//            }
//        }
//
//        JButton button = new JButton(title);
//        button.addActionListener(new NewListener());
//        return button;
//    }
//
//    private boolean addDay() {
//        boolean dateAdded = false;
//        String inputs;
//        do {
//            try {
//                inputs = JOptionPane.showInputDialog(null, "Enter Name");
//                String[] dayFields = inputs.split("-");
//                int year = Integer.parseInt(dayFields[0]);
//                int month = Integer.parseInt(dayFields[1]);
//                int date = Integer.parseInt(dayFields[2]);
//                mainPanel.setDay(new Day(year, month, date));
//                dateAdded = true;
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(null, "Invalid input");
//            } catch (DateTimeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage());
//            }
//        } while (!dateAdded);
//        return dateAdded;
//    }
//
//    private JButton createSelectButton(String title) {
//        class LoadListener implements ActionListener {
//            public void actionPerformed(ActionEvent event) {
//                boolean dateAdded = false;
//                String date;
//                do {
//                    date = JOptionPane.showInputDialog(null, "Enter Name");
//                    mainPanel.setJsonLocation(date);
//                    try {
//                        mainPanel.setDay(mainPanel.getJsonReader().read());
//                        JOptionPane.showMessageDialog(null, "File loaded from: " + date);
//                        dateAdded = true;
//                        setVisible(false);
//                    } catch (IOException e) {
//                        JOptionPane.showMessageDialog(null, "File could not be found, please try again");
//                        mainPanel.resetLocation();
//                    }
//                } while (!dateAdded);
//            }
//        }
//
//        JButton button = new JButton(title);
//        button.addActionListener(new LoadListener());
//        return button;
//    }
//}
//
