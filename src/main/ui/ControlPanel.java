package ui;

import model.Day;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class ControlPanel extends JPanel {
    private String jsonLocation = "./data/";
    private final File errorSoundFile = new File("data/Sounds/Error.wav").getAbsoluteFile();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Day day;
    private final StartPanel startPanel;
    private final DatePanel datePanel;
    private final LoadPanel loadPanel;
    private final OperationPanel operationPanel;
    private final AddPanel addPanel;
    private final DetailsPanel detailsPanel;
    private static final int FIELD_WIDTH = 5;


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
                    String date = LocalDate.now().toString();
                    setJsonLocation(date);
                    try {
                        setDay(getJsonReader().read());
                        JOptionPane.showMessageDialog(null, "File loaded from: " + date);
                        operationPanel.setVisible(true);
                        setVisible(false);
                    } catch (IOException e) {
                        playClip(errorSoundFile);
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
                    do {
                        String date = JOptionPane.showInputDialog(null, "Enter date in this format: YYYY-MM-DD");
                        setJsonLocation(date);
                        try {
                            setDay(getJsonReader().read());
                            JOptionPane.showMessageDialog(null, "File loaded from: " + date);
                            dateAdded = true;
                            operationPanel.setVisible(true);
                            setVisible(false);
                        } catch (IOException e) {
                            playClip(errorSoundFile);
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

        private boolean addDay() {
            boolean dateAdded = false;
            String inputs;
            do {
                try {
                    inputs = JOptionPane.showInputDialog(null, "Enter date in this format: YYYY-MM-DD");
                    String[] dayFields = inputs.split("-");
                    int year = Integer.parseInt(dayFields[0]);
                    int month = Integer.parseInt(dayFields[1]);
                    int date = Integer.parseInt(dayFields[2]);
                    setDay(new Day(year, month, date));
                    dateAdded = true;
                } catch (NumberFormatException e) {
                    playClip(errorSoundFile);
                    JOptionPane.showMessageDialog(null, "Invalid input");

                } catch (DateTimeException e) {
                    playClip(errorSoundFile);
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (NullPointerException e) {
                    playClip(errorSoundFile);
                    break;
                }
            } while (!dateAdded);
            return dateAdded;
        }

        private JButton createSelectButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    boolean dayAdded = false;
                    do {
                        dayAdded = addDay();
                    } while (!dayAdded);
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
        //        private JLabel label = new JLabel("What would you like to add");
//        private JTextField name = new JTextField(FIELD_WIDTH);
//        private JTextArea description = new JTextArea(5,FIELD_WIDTH * 3);
//        private JTextField calories = new JTextField(FIELD_WIDTH);
//        private JLabel nameLabel = new JLabel("Name: ");
//        private JLabel descLabel = new JLabel("Description (optional): ");
//        private JLabel caloriesLabel = new JLabel("Calories: ");
//        private JButton submitButton;
//        private JRadioButton mealButton = new JRadioButton("Meal");
//        private JRadioButton exerciseButton = new JRadioButton("Exercise");
//        private JLabel calorieLabel;
//        private ButtonGroup buttonGroup = new ButtonGroup();
//
        private JLabel label;
        private JTextField name;
        private JTextArea description;
        private JTextField calories;
        private JLabel caloriesLabel;
        private JLabel nameLabel;
        private JLabel descLabel;
        private JButton submitButton;
        private JRadioButton mealButton;
        private JRadioButton exerciseButton;
        private JPanel panel;
        private ButtonGroup buttonGroup;

//        public AddPanel() {
//            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//            setandAddComponent(this, label);
//            addComponent(this, mealButton, exerciseButton);
//            buttonGroup.add(mealButton);
//            buttonGroup.add(exerciseButton);
//            addComponent(this, nameLabel, name, caloriesLabel, calories);
//            addComponent(this, descLabel, description);
//            submitButton = createSubmitButton("Submit");
//            setandAddComponent(this,submitButton);
//        }
//
//        public void addComponent(JPanel panel, JComponent j1, JComponent j2,JComponent j3,JComponent j4) {
//            JPanel jpanel = new JPanel();
//            jpanel.add(j1);
//            jpanel.add(j2);
//            jpanel.add(Box.createRigidArea(new Dimension(50,0)));
//            jpanel.add(j3);
//            jpanel.add(j4);
//            jpanel.setAlignmentX(CENTER_ALIGNMENT);
//            panel.add(jpanel);
//            panel.add(Box.createRigidArea(new Dimension(0,10)));
//        }
//
//        public void addComponent(JPanel panel, JComponent j1, JComponent j2) {
//            JPanel jpanel = new JPanel();
//            jpanel.add(j1);
//            jpanel.add(j2);
//            jpanel.setAlignmentX(CENTER_ALIGNMENT);
//            panel.add(jpanel);
//            panel.add(Box.createRigidArea(new Dimension(0,10)));
//        }

        private JButton createSubmitButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    int calorieCount;
                    try {
                        calorieCount = Integer.parseInt(calories.getText());
                        if (mealButton.isSelected()) {
                            day.addMeal(name.getText(),description.getText(), calorieCount);
                            JOptionPane.showMessageDialog(null, "Meal added Successfully");
                        } else if (exerciseButton.isSelected()) {
                            day.addExercise(name.getText(),description.getText(), calorieCount);
                            JOptionPane.showMessageDialog(null, "Exercise added Successfully");
                        }
                        setVisible(false);
                        operationPanel.setVisible(true);
                    } catch (NumberFormatException e) {
                        playClip(errorSoundFile);
                        JOptionPane.showMessageDialog(null, "Error in Calories field, please try again");
                    }
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }

        private void createUIComponents() {
            panel = this;
            submitButton = createSubmitButton("Submit");
        }
    }

    class DetailsPanel extends JPanel {

        private ButtonGroup buttonGroup;
        private JRadioButton mealListRadioButton;
        private JRadioButton mealDetailRadioButton;
        private JRadioButton exerciseListRadioButton;
        private JRadioButton exerciseDetailRadioButton;
        private JButton submitButton;
        private JLabel label;
        private JPanel panel;

        private JButton createSubmitButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    String message = "";
                    if (mealListRadioButton.isSelected()) {
                        message = day.mealNames();
                    } else if (mealDetailRadioButton.isSelected()) {
                        message = day.mealAllDetails();
                    } else if (exerciseListRadioButton.isSelected()) {
                        message = day.exerciseNames();
                    } else {
                        message = day.exerciseAllDetails();
                    }
                    if (message.equals("")) {
                        message = "List is empty";
                    }

                    JOptionPane.showMessageDialog(null, message);
                    setVisible(false);
                    operationPanel.setVisible(true);
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }

        private void createUIComponents() {
            panel = this;
            submitButton = createSubmitButton("Submit");

        }
    }

    private class OperationPanel extends JPanel {
        private final JLabel label;
        private final JButton addButton;
        private final JButton calorieButton;
        private final JButton detailsButton;
        private final JButton saveButton;
        private final JButton exitButton;

        public OperationPanel() {
            label = new JLabel("What would you like to do");
            addButton = createAddButton("Add a meal/exercise");
            calorieButton = createCalorieButton("Check calorie summary for this date");
            detailsButton = createDetailsButton("Check details about meals and/or exercises done this date");
            saveButton = createSaveButton("Save records");
            exitButton = createExitButton("Exit application");
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setandAddComponent(this, label);
            setandAddComponent(this, addButton);
            setandAddComponent(this, calorieButton);
            setandAddComponent(this, detailsButton);
            setandAddComponent(this, saveButton);
            setandAddComponent(this, exitButton);
        }

        private JButton createAddButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    setVisible(false);
                    addPanel.setVisible(true);
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }

        private JButton createCalorieButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    StringBuilder string = new StringBuilder();
                    string.append("Calories Summary for: " + day.getDate().toString());
                    string.append("\n\nCalories in: " + day.caloriesIn());
                    string.append("\nCalories out: " + day.caloriesOut());
                    string.append("\nCalories total: " + day.caloricTotal());
                    JOptionPane.showMessageDialog(null,string.toString());
                }
            }

            JButton button = new JButton(title);
            button.addActionListener(new LoadListener());
            return button;
        }

        private JButton createDetailsButton(String title) {
            class LoadListener implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                    setVisible(false);
                    detailsPanel.setVisible(true);
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
                        JOptionPane.showMessageDialog(null, "File saved to: " + jsonLocation);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "File not saved to: " + jsonLocation);
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


    public ControlPanel() {
        startPanel = new StartPanel();
        datePanel = new DatePanel();
        loadPanel = new LoadPanel();
        operationPanel = new OperationPanel();
        addPanel = new AddPanel();
        detailsPanel = new DetailsPanel();


        this.add(datePanel);
        this.add(startPanel);
        this.add(loadPanel);
        this.add(operationPanel);
        this.add(addPanel);
        this.add(detailsPanel);
        datePanel.setVisible(false);
        loadPanel.setVisible(false);
        operationPanel.setVisible(false);
        addPanel.setVisible(false);
        detailsPanel.setVisible(false);
    }


    // modeled after https://stackoverflow.com/questions/577724/trouble-playing-wav-in-java/577926#577926
    static class AudioListener implements LineListener {
        private boolean done = false;

        @Override
        public synchronized void update(LineEvent event) {
            LineEvent.Type eventType = event.getType();
            if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                done = true;
                notifyAll();
            }
        }

        public synchronized void waitUntilDone() throws InterruptedException {
            while (!done) {
                wait();
            }
        }
    }

    // modeled after https://stackoverflow.com/questions/577724/trouble-playing-wav-in-java/577926#577926
    private static void playClip(File clipFile) {
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(clipFile);
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
                clip.start();
                listener.waitUntilDone();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                clip.close();
            }
            audioInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setandAddComponent(JPanel panel, JComponent j) {
        j.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(j);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
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
