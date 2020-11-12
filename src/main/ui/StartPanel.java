//package ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class StartPanel extends JPanel {
//    private JLabel label;
//    private JButton newButton;
//    private JButton loadButton;
//    private MainPanel mainPanel;
//
//    public StartPanel() {
//        label = new JLabel("Would you like to add a new day or load a previous day?");
//        newButton = newDayButton("New Day");
//        loadButton = loadDayButton("Load a previous date");
//        //this.setLayout(new GridLayout(3,1));
//        newButton.setAlignmentX(CENTER_ALIGNMENT);
//        label.setAlignmentX(CENTER_ALIGNMENT);
//        loadButton.setAlignmentX(CENTER_ALIGNMENT);
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.add(Box.createRigidArea(new Dimension(0,20)));
//        this.add(label);
//        this.add(Box.createRigidArea(new Dimension(0,50)));
//        this.add(newButton);
//        this.add(Box.createRigidArea(new Dimension(0,20)));
//        this.add(loadButton);
//    }
//
//    public StartPanel(MainPanel main) {
//        mainPanel = main;
//        label = new JLabel("Would you like to add a new day or load a previous day?");
//        newButton = newDayButton("New Day");
//        loadButton = loadDayButton("Load a previous date");
//        //this.setLayout(new GridLayout(3,1));
//        newButton.setAlignmentX(CENTER_ALIGNMENT);
//        label.setAlignmentX(CENTER_ALIGNMENT);
//        loadButton.setAlignmentX(CENTER_ALIGNMENT);
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.add(Box.createRigidArea(new Dimension(0,20)));
//        this.add(label);
//        this.add(Box.createRigidArea(new Dimension(0,50)));
//        this.add(newButton);
//        this.add(Box.createRigidArea(new Dimension(0,20)));
//        this.add(loadButton);
//    }
//
//
//    private static JButton newDayButton(String title) {
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
//
//            }
//        }
//
//        JButton button = new JButton(title);
//        button.addActionListener(new NewListener());
//        return button;
//    }
//
//    private static JButton loadDayButton(String title) {
//        class LoadListener implements ActionListener {
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
//
//            }
//        }
//
//        JButton button = new JButton(title);
//        button.addActionListener(new LoadListener());
//        return button;
//    }
//}
