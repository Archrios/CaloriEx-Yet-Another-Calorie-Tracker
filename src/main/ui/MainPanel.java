package ui;

import model.Day;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class MainPanel extends JPanel {
    private String jsonLocation = "./data/";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Day day;
    private StartPanel startPanel;
    private DatePanel datePanel;
    private LoadPanel loadPanel;
    private OperationPanel operationPanel;


    private class StartPanel extends JPanel {
        private final JLabel label;
        private final JButton newButton;
        private final JButton loadButton;

        public StartPanel() {
            label = new JLabel("Would you like to add a new day or load a previous day?");
            newButton = newDayButton("New Day");
            loadButton = loadDayButton("Load a previous date");
            //this.setLayout(new GridLayout(3,1));
            newButton.setAlignmentX(CENTER_ALIGNMENT);
            label.setAlignmentX(CENTER_ALIGNMENT);
            loadButton.setAlignmentX(CENTER_ALIGNMENT);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(label);
            this.add(Box.createRigidArea(new Dimension(0, 50)));
            this.add(newButton);
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(loadButton);
        }


        private JButton newDayButton(String title) {
            class NewListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
//                String get = text.getText();
//                if (get.equals("hello")) {
//                    text.setVisible(false);
//
//                    text1 = new JTextField(10);
//                    text1.setText("fuuuuuck you");
//                    tester.add(text1);
//                    frame.revalidate();
//                    frame.repaint();
//                }
                    setVisible(false);
                    datePanel.setVisible(true);

                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new NewListener());
            return button;
        }

        private JButton loadDayButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
//                String get = text.getText();
//                if (get.equals("hello")) {
//                    text.setVisible(false);
//
//                    text1 = new JTextField(10);
//                    text1.setText("fuuuuuck you");
//                    tester.add(text1);
//                    frame.revalidate();
//                    frame.repaint();
//                }
                    setVisible(false);
                    loadPanel.setVisible(true);
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }
    }

    private class LoadPanel extends JPanel {
        private final JLabel label;
        private final JButton todayButton;
        private final JButton selectButton;

        public LoadPanel() {
            label = new JLabel("Which day would you like to load from?");
            todayButton = createTodayButton("Today");
            selectButton = createSelectButton("Select own date");
            todayButton.setAlignmentX(CENTER_ALIGNMENT);
            label.setAlignmentX(CENTER_ALIGNMENT);
            selectButton.setAlignmentX(CENTER_ALIGNMENT);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(label);
            this.add(Box.createRigidArea(new Dimension(0, 50)));
            this.add(todayButton);
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(selectButton);
        }

        private JButton createTodayButton(String title) {
            class NewListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
//                String get = text.getText();
//                if (get.equals("hello")) {
//                    text.setVisible(false);
//
//                    text1 = new JTextField(10);
//                    text1.setText("fuuuuuck you");
//                    tester.add(text1);
//                    frame.revalidate();
//                    frame.repaint();
//                }
                    String date = LocalDate.now().toString();
                    setJsonLocation(date);
                    try {
                        setDay(getJsonReader().read());
                        JOptionPane.showMessageDialog(null, "File loaded from: " + date);
                        operationPanel.setVisible(true);
                        setVisible(false);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "File could not be found, please try again");
                        resetLocation();
                    }
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new NewListener());
            return button;
        }

        private JButton createSelectButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    boolean dateAdded = false;
                    String date;
                    do {
                        date = JOptionPane.showInputDialog(null, "Enter Name");
                        setJsonLocation(date);
                        try {
                            setDay(getJsonReader().read());
                            JOptionPane.showMessageDialog(null, "File loaded from: " + date);
                            dateAdded = true;
                            operationPanel.setVisible(true);
                            setVisible(false);
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "File could not be found, please try again");
                            resetLocation();
                        }
                    } while (!dateAdded);
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }
    }

    private class DatePanel extends JPanel {
        private final JLabel label;
        private final JButton todayButton;
        private final JButton selectButton;

//    public DatePanel() {
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

        public DatePanel() {
            label = new JLabel("Which day would you like to add to?");
            todayButton = createTodayButton("Today");
            selectButton = createSelectButton("Select own date");
            todayButton.setAlignmentX(CENTER_ALIGNMENT);
            label.setAlignmentX(CENTER_ALIGNMENT);
            selectButton.setAlignmentX(CENTER_ALIGNMENT);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(label);
            this.add(Box.createRigidArea(new Dimension(0, 50)));
            this.add(todayButton);
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(selectButton);
        }

        private JButton createTodayButton(String title) {
            class NewListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
//                String get = text.getText();
//                if (get.equals("hello")) {
//                    text.setVisible(false);
//
//                    text1 = new JTextField(10);
//                    text1.setText("fuuuuuck you");
//                    tester.add(text1);
//                    frame.revalidate();
//                    frame.repaint();
//                }
                    setDay(new Day());
                    setJsonLocation();
                    operationPanel.setVisible(true);
                    setVisible(false);
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new NewListener());
            return button;
        }

        private void addDay() {
            boolean dateAdded = false;
            String inputs;
            do {
                try {
                    inputs = JOptionPane.showInputDialog(null, "Enter Name");
                    String[] dayFields = inputs.split("-");
                    int year = Integer.parseInt(dayFields[0]);
                    int month = Integer.parseInt(dayFields[1]);
                    int date = Integer.parseInt(dayFields[2]);
                    setDay(new Day(year, month, date));
                    dateAdded = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                } catch (DateTimeException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } while (!dateAdded);
        }

        private JButton createSelectButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    addDay();
                    setJsonLocation();
                    operationPanel.setVisible(true);
                    setVisible(false);
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }
    }

    private class AddPanel extends JPanel {
        private JLabel label;
        private JTextField name;
        private JTextField description;
        private JTextField calories;
        private JLabel nameLabel = new JLabel("Name: ");
        private JLabel descLabel = new JLabel("Description: ");
        private JLabel caloriesLabel = new JLabel("Calories: ");
        private JButton submitButton;

        public AddPanel() {
        }

        private JButton createSubmitButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {

                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }


    }

    private class OperationPanel extends JPanel {
        private final JLabel label;
        private JButton addButton;
        private JButton calorieButton;
        private JButton detailsButton;
        private JButton saveButton;
        private JButton exitButton;

        public OperationPanel() {
            label = new JLabel("What would you like to do");
            addButton = createAddButton("Add a meal/exercise");
            calorieButton = createCalorieButton("Check calorie summary for this date");
            detailsButton = createDetailsButton("Check details about meals and/or exercises done this date");
            saveButton = createSaveButton("Save records");
            exitButton = createExitButton("Exit application");
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setandAddComponent(this,label);
            setandAddComponent(this,addButton);
            setandAddComponent(this,calorieButton);
            setandAddComponent(this,detailsButton);
            setandAddComponent(this,saveButton);
            setandAddComponent(this,exitButton);
        }

        private JButton createAddButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {

                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }

        private JButton createCalorieButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {

                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }

        private JButton createDetailsButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {

                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }

        private JButton createSaveButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(day);
                        jsonWriter.close();
                        JOptionPane.showMessageDialog(null,"File saved to: " + jsonLocation);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null,"File not saved to: " + jsonLocation);
                    }
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }

        private JButton createExitButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {

                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }
    }


    public MainPanel() {
        startPanel = new StartPanel();
        datePanel = new DatePanel();
        loadPanel = new LoadPanel();
        operationPanel = new OperationPanel();

        this.add(datePanel);
        this.add(startPanel);
        this.add(loadPanel);
        this.add(operationPanel);
        datePanel.setVisible(false);
        loadPanel.setVisible(false);
        operationPanel.setVisible(false);
    }

    public void setandAddComponent(JPanel panel, JComponent j) {
        j.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(j);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
    }

    public void setDay(Day date) {
        day = date;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    //MODIFIES: this
    // resets the read/write location jsonLocation to default
    public void resetLocation() {
        jsonLocation = "./data/";
    }

    public void setJsonLocation() {
        jsonLocation += (day.getDate().toString() + ".json");
        jsonWriter = new JsonWriter(jsonLocation);
        jsonReader = new JsonReader(jsonLocation);
    }

    public void setJsonLocation(String date) {
        jsonLocation += (date + ".json");
        jsonWriter = new JsonWriter(jsonLocation);
        jsonReader = new JsonReader(jsonLocation);
    }

}
