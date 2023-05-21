package com.example.employeemanagement

import javafx.application.Application
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.transformation.FilteredList
import javafx.collections.transformation.SortedList
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.*
import java.util.*

class EmployeeManagement : Application() {
    private val employees = FXCollections.observableArrayList<Employee>()
    private val filteredEmployees = FilteredList(employees)
    private val sortedEmployees = SortedList(filteredEmployees)
    private var sortAscending = true
    override fun start(primaryStage: Stage) {
        // Create UI elements
        val employeeListView = ListView(sortedEmployees)
        val searchField = TextField()
        searchField.promptText = "Search by Last Name"
        searchField.textProperty().addListener { observable: ObservableValue<out String?>?, oldValue: String?, newValue: String? ->
            // Update filtered employees based on search text
            filteredEmployees.setPredicate { employee: Employee ->
                if (newValue == null || newValue.isEmpty()) {
                    return@setPredicate true
                }
                val lowerCaseFilter = newValue.lowercase(Locale.getDefault())
                employee.lastName.lowercase(Locale.getDefault()).contains(lowerCaseFilter)
            }
        }
        val lastNameField = TextField()
        lastNameField.promptText = "Last Name"
        val positionField = TextField()
        positionField.promptText = "Position"
        val departmentField = TextField()
        departmentField.promptText = "Department"
        val badgeNumberField = TextField()
        badgeNumberField.promptText = "Badge Number"
        val isPresentCheckBox = CheckBox("Is Present")
        val lastEntryExitTimeField = TextField()
        lastEntryExitTimeField.promptText = "Last Entry/Exit Time"
        val contextMenu = ContextMenu()
        val removeItem = MenuItem("Remove")
        removeItem.onAction = EventHandler { e: ActionEvent? ->
            // Remove selected employee from list
            val selectedEmployee = employeeListView.selectionModel.selectedItem
            employees.remove(selectedEmployee)
        }
        contextMenu.items.add(removeItem)
        employeeListView.contextMenu = contextMenu
        val addButton = Button("Add Employee")
        addButton.onAction = EventHandler<ActionEvent> { event: ActionEvent? ->
            // Add employee to list
            val lastName = lastNameField.text
            val position = positionField.text
            val department = departmentField.text
            val badgeNumber = badgeNumberField.text.toInt()
            val isPresent = isPresentCheckBox.isSelected
            val lastEntryExitTime = lastEntryExitTimeField.text
            val employee = Employee(lastName, position, department, badgeNumber, isPresent, lastEntryExitTime)
            employees.add(employee)

            // Clear input fields
            lastNameField.clear()
            positionField.clear()
            departmentField.clear()
            badgeNumberField.clear()
            isPresentCheckBox.isSelected = false
            lastEntryExitTimeField.clear()
        }
        val saveButton = Button("Save Employees to File")
        saveButton.onAction = EventHandler { event: ActionEvent? ->
            // Save employees to file
            val fileChooser = FileChooser()
            fileChooser.title = "Save Employees to File"
            val file = fileChooser.showSaveDialog(primaryStage)
            if (file != null) {
                try {
                    ObjectOutputStream(FileOutputStream(file)).use { out -> out.writeObject(ArrayList(employees)) }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        val loadButton = Button("Load Employees from File")
        loadButton.onAction = EventHandler { event: ActionEvent? ->
            // Load employees from file
            val fileChooser = FileChooser()
            fileChooser.title = "Load Employees from File"
            val file = fileChooser.showOpenDialog(primaryStage)
            if (file != null) {
                try {
                    ObjectInputStream(FileInputStream(file)).use { `in` -> employees.setAll(`in`.readObject() as List<Employee>) }
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
        val sortButton = Button("Sort by Last Name")
        sortButton.onAction = EventHandler { event: ActionEvent? ->
            // Sort employees by last name
            val comparator: Comparator<Employee>
            if (sortAscending) {
                comparator = Comparator.comparing(Employee::lastName)
                sortAscending = false
                sortButton.text = "Sort by Last Name (Descending)"
            } else {
                comparator = Comparator.comparing(Employee::lastName).reversed()
                sortAscending = true
                sortButton.text = "Sort by Last Name (Ascending)"
            }
            sortedEmployees.setComparator(comparator)
        }
        val presentToggleGroup = ToggleGroup()
        val allRadioButton = RadioButton("All")
        allRadioButton.toggleGroup = presentToggleGroup
        allRadioButton.isSelected = true
        val presentRadioButton = RadioButton("Present")
        presentRadioButton.toggleGroup = presentToggleGroup
        val notPresentRadioButton = RadioButton("Not Present")
        notPresentRadioButton.toggleGroup = presentToggleGroup
        presentToggleGroup.selectedToggleProperty().addListener { observable: ObservableValue<out Toggle>?, oldValue: Toggle?, newValue: Toggle ->
            // Update filtered employees based on selected toggle
            if (newValue === allRadioButton) {
                filteredEmployees.setPredicate { employee: Employee? -> true }
            } else if (newValue === presentRadioButton) {
                filteredEmployees.setPredicate { employee: Employee -> employee.isPresent }
            } else if (newValue === notPresentRadioButton) {
                filteredEmployees.setPredicate { employee: Employee -> !employee.isPresent }
            }
        }
        val dataHBox = HBox(10.0, loadButton, saveButton)
        dataHBox.spacing = 10.0
        dataHBox.alignment = Pos.CENTER
        dataHBox.padding = Insets(10.0)
        val presentHBox = HBox(10.0, allRadioButton, presentRadioButton, notPresentRadioButton, sortButton)
        presentHBox.spacing = 10.0
        presentHBox.padding = Insets(10.0)
        presentHBox.alignment = Pos.CENTER
        val tableVBox = VBox(searchField, presentHBox, employeeListView)
        val newRecordVBox = VBox(lastNameField, positionField, departmentField, badgeNumberField, isPresentCheckBox, lastEntryExitTimeField, addButton)
        newRecordVBox.spacing = 10.0
        newRecordVBox.alignment = Pos.CENTER
        newRecordVBox.padding = Insets(10.0)

        // Create layout and scene
        val root = BorderPane()
        root.bottom = dataHBox
        root.left = tableVBox
        root.right = newRecordVBox
        root.padding = Insets(10.0)
        val scene = Scene(root)

        // Set up stage
        primaryStage.scene = scene
        primaryStage.title = "Employee List"
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(EmployeeManagement::class.java)
        }
    }
}
