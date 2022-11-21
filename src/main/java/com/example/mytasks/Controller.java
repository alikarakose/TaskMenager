package com.example.mytasks;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    public ListView<String> taskList;
    @FXML
    public TextField newTask;
    public String filePath = "C:\\Users\\Akademie\\Desktop\\myTasks\\src\\main\\java\\com\\example\\mytasks\\data.txt";
    public File data = new File(filePath);

    @FXML
    public void addNewTask(ActionEvent e) {
        String text = newTask.getText();
        if (!text.equals("")) {
            taskList.getItems().add(text);
            newTask.setText("");
        } else {
            System.out.println("No input..");
        }
    }
    @FXML
    public void deleteTask(ActionEvent e) {
        String selected = taskList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            taskList.getItems().remove(selected);
        } else {
            System.out.println("No task selected..");
        }
    }

    public void exitProgram(ActionEvent e) {
        System.out.println("Exiting program..");
        ObservableList<String> currentTaskList = taskList.getItems();

        try {
            FileWriter writer = new FileWriter(filePath);

            for (String text: currentTaskList) {
                text += "\n";
                writer.write(text);
            }

            writer.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Scanner myReader = new Scanner(data);

            while (myReader.hasNextLine()) {
                String text = myReader.nextLine();
                taskList.getItems().add(text);
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
