//package ui;
//
//import model.Day;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.time.DateTimeException;
//import java.time.LocalDate;
//
//public class DatePanel extends JPanel {
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
//    public DatePanel(MainPanel main) {
//        mainPanel = main;
//        label = new JLabel("Which day would you like to add to?");
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
//                mainPanel.setDay(new Day());
//                mainPanel.setJsonLocation();
//                setVisible(false);
//            }
//        }
//
//        JButton button = new JButton(title);
//        button.addActionListener(new NewListener());
//        return button;
//    }
//
//    private void addDay() {
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
//    }
//
//    private JButton createSelectButton(String title) {
//        class LoadListener implements ActionListener {
//            public void actionPerformed(ActionEvent event) {
//                addDay();
//                mainPanel.setJsonLocation();
//                setVisible(false);
//            }
//        }
//
//        JButton button = new JButton(title);
//        button.addActionListener(new LoadListener());
//        return button;
//    }
//}
