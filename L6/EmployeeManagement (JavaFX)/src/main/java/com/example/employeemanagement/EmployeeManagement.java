package com.example.employeemanagement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagement extends Application {
    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private FilteredList<Employee> filteredEmployees = new FilteredList<>(employees);
    private SortedList<Employee> sortedEmployees = new SortedList<>(filteredEmployees);
    private boolean sortAscending = true;

    @Override
    public void start(Stage primaryStage) {
        // Create UI elements
        ListView<Employee> employeeListView = new ListView<>(sortedEmployees);
        TextField searchField = new TextField();
        searchField.setPromptText("Search by Last Name");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Update filtered employees based on search text
            filteredEmployees.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return employee.getLastName().toLowerCase().contains(lowerCaseFilter);
            });
        });;
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField positionField = new TextField();
        positionField.setPromptText("Position");
        TextField departmentField = new TextField();
        departmentField.setPromptText("Department");
        TextField badgeNumberField = new TextField();
        badgeNumberField.setPromptText("Badge Number");
        CheckBox isPresentCheckBox = new CheckBox("Is Present");
        TextField lastEntryExitTimeField = new TextField();
        lastEntryExitTimeField.setPromptText("Last Entry/Exit Time");

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(event -> {
            // Add employee to list
            String lastName = lastNameField.getText();
            String position = positionField.getText();
            String department = departmentField.getText();
            int badgeNumber = Integer.parseInt(badgeNumberField.getText());
            boolean isPresent = isPresentCheckBox.isSelected();
            String lastEntryExitTime = lastEntryExitTimeField.getText();

            Employee employee = new Employee(lastName, position, department, badgeNumber, isPresent, lastEntryExitTime);
            employees.add(employee);

            // Clear input fields
            lastNameField.clear();
            positionField.clear();
            departmentField.clear();
            badgeNumberField.clear();
            isPresentCheckBox.setSelected(false);
            lastEntryExitTimeField.clear();
        });
        
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Remove");
        removeItem.setOnAction(e -> {
            // Remove selected employee from list
            Employee selectedEmployee = employeeListView.getSelectionModel().getSelectedItem();
            employees.remove(selectedEmployee);
        });
        contextMenu.getItems().add(removeItem);
        employeeListView.setContextMenu(contextMenu);

        Button sortButton = new Button("Sort by Last Name");
        sortButton.setOnAction(event -> {
            // Sort employees by last name
            Comparator<Employee> comparator;
            if (sortAscending) {
                comparator = Comparator.comparing(Employee::getLastName);
                sortAscending = false;
                sortButton.setText("Sort by Last Name (Descending)");
            } else {
                comparator = Comparator.comparing(Employee::getLastName).reversed();
                sortAscending = true;
                sortButton.setText("Sort by Last Name (Ascending)");
            }
            sortedEmployees.setComparator(comparator);
        });

        Button saveButton = new Button("Save Employees to File");
        saveButton.setOnAction(event -> {
            // Save employees to file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Employees to File");
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                    out.writeObject(new ArrayList<>(employees));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button loadButton = new Button("Load Employees from File");
        loadButton.setOnAction(event -> {
            // Load employees from file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Employees from File");
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    employees.setAll((List<Employee>) in.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        ToggleGroup presentToggleGroup = new ToggleGroup();
        RadioButton allRadioButton = new RadioButton("All");
        allRadioButton.setToggleGroup(presentToggleGroup);
        allRadioButton.setSelected(true);
        RadioButton presentRadioButton = new RadioButton("Present");
        presentRadioButton.setToggleGroup(presentToggleGroup);
        RadioButton notPresentRadioButton = new RadioButton("Not Present");
        notPresentRadioButton.setToggleGroup(presentToggleGroup);

        presentToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            // Update filtered employees based on selected toggle
            if (newValue == allRadioButton) {
                filteredEmployees.setPredicate(employee -> true);
            } else if (newValue == presentRadioButton) {
                filteredEmployees.setPredicate(Employee::isPresent);
            } else if (newValue == notPresentRadioButton) {
                filteredEmployees.setPredicate(employee -> !employee.isPresent());
            }
        });

        HBox dataHBox = new HBox(10, loadButton, saveButton);
        dataHBox.setSpacing(10);
        dataHBox.setAlignment(Pos.CENTER);
        dataHBox.setPadding(new Insets(10));
        
        HBox presentHBox = new HBox(10, allRadioButton, presentRadioButton, notPresentRadioButton, sortButton); presentHBox.setSpacing(10);
        presentHBox.setPadding(new Insets(10));
        presentHBox.setAlignment(Pos.CENTER);
        
        VBox tableVBox = new VBox(searchField, presentHBox, employeeListView);
        
        VBox newRecordVBox = new VBox(lastNameField, positionField, departmentField, badgeNumberField, isPresentCheckBox, lastEntryExitTimeField, addButton);
        newRecordVBox.setSpacing(10);
        newRecordVBox.setAlignment(Pos.CENTER);
        newRecordVBox.setPadding(new Insets(10));

        // Create layout and scene
        BorderPane root = new BorderPane();
        root.setBottom(dataHBox);
        root.setLeft(tableVBox);
        root.setRight(newRecordVBox);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root);

        // Set up stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Employee List");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
