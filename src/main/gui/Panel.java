package gui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Panel extends JPanel
        implements ListSelectionListener {

    private static final String addString = "Add";
    private static final String deleteString = "Delete";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private JList list;
    private DefaultListModel toDoListModel = new DefaultListModel();
    SimpleDateFormat sdf;

    {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    JLabel taskName = new JLabel("Task Name: ");
    JTextField taskNmTextField = new JTextField(10);
    JButton addButton = new JButton(addString);
    JButton removeButton = new JButton(deleteString);
    JButton saveButton = new JButton(saveString);
    JButton loadButton = new JButton(loadString);

    public Panel() throws Exception {
        super(new BorderLayout());
        //Create the list and put it in a scroll pane.
        list = new JList(toDoListModel);
        toDoListModel.addElement("lab1");
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(20);
        JScrollPane listScrollPane = new JScrollPane(list);
        this.buildButtonAndPane();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        JPanel myFlowPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myFlowPanel1.add(taskName);
        myFlowPanel1.add(taskNmTextField);
        JPanel gridPanel = new JPanel(new GridLayout());
        gridPanel.add(myFlowPanel1);
        gridPanel.add(buttonPane);
        add(listScrollPane, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
    }


    private void buildButtonAndPane() {
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        removeButton.setActionCommand(deleteString);
        removeButton.addActionListener(new RemoveListener());

        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());

        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());
        taskNmTextField = new JTextField(12);
        taskNmTextField.addActionListener(addListener);
        taskNmTextField.getDocument().addDocumentListener(addListener);
    }
    //***


    // Return all tasks name to a todolist
    public ToDoList modelToToDoList(ListModel toDoListModel) throws ParseException {
        ToDoList toDoList = new ToDoList("TDL1");
        for (int i = 0; i < toDoListModel.getSize(); i++) {
            toDoList.addTask(toDoListModel.getElementAt(i).toString(), sdf.parse("1111-11-11"),
                    "nonStatus");
        }
        return toDoList;
    }

    private void playSound() throws Exception {
        File soundFile = new File("smu.wav");
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) throws Exception {
        if (e.getActionCommand().equals("Add")) {
            playSound();
        }
    }

    //This listener is shared by the text field and the deleteButton.
    private class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //and remove the line that's selected.
            int index = list.getSelectedIndex();
            toDoListModel.remove(index);
            try {
                modelToToDoList(toDoListModel);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            int size = toDoListModel.getSize();

            if (size == 0) { //No stock in the list, disable remove.
                removeButton.setEnabled(false);
            } else { //Select an index.
                if (index == toDoListModel.getSize()) {
                    //removed item in last position
                    index--;
                }
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }


    //This listener is shared by the text field and the addButton.
    private class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;

        public AddListener(JButton button) {
            addButton = button;
        }

        // Required by ActionListener
        public void actionPerformed(ActionEvent e) {
            String taskName = taskNmTextField.getText();

            //User didn't type in a unique name...
            if (taskName.equals("") || alreadyInList(taskName)) {
                Toolkit.getDefaultToolkit().beep();
                taskNmTextField.requestFocusInWindow();
                taskNmTextField.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            toDoListModel.insertElementAt(taskNmTextField.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //watchlistModel.addElement(stockSymTextField.getText());
            try {
                modelToToDoList(toDoListModel);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            //Reset the text field.
            taskNmTextField.requestFocusInWindow();
            taskNmTextField.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }


        protected boolean alreadyInList(String taskName) {
            return toDoListModel.contains(taskName);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                addButton.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                addButton.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            //No selection, disable remove button.
            //Selection, enable the remove button.
            removeButton.setEnabled(list.getSelectedIndex() != -1);
        }
    }

    private class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                doLoadCommand("/Users/wenka/IdeaProjects/CPSC210/labs/project_q6d3d/data/todolist.json");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        public void doLoadCommand(String filename) throws Exception {
            JsonReader reader = new JsonReader(filename);
            ToDoList toDoList = reader.read();
            toDoListModel.removeAllElements();
            for (Task task : toDoList.getTasks()) {
                toDoListModel.addElement(task.getName());
            }
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                doSaveCommand(toDoListModel,
                        "/Users/wenka/IdeaProjects/CPSC210/labs/project_q6d3d/data/todolist.json");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public void doSaveCommand(DefaultListModel toDoListModel, String filename) throws Exception {
        JsonWriter writer = new JsonWriter(filename);
        writer.open();
        writer.write(modelToToDoList(toDoListModel));
        writer.close();
        playSound();
    }
}
