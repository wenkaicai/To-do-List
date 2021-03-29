package gui;

import javax.swing.*;
import java.awt.*;

// Create ToDoListGui Frame
public class Frame extends JFrame {
    Panel panel = new Panel();

    Frame() throws Exception {
        this.setTitle("My Task Todolist");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of app
        this.setResizable(false); // prevent frame from being resized
        this.setSize(460, 460);
        this.getContentPane().setBackground(new Color(172, 235, 235)); // background color
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }
}