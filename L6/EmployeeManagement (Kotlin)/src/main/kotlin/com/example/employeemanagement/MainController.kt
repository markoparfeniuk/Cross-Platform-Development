package com.example.employeemanagement

import javafx.fxml.FXML
import javafx.scene.control.Label

class MainController {
    @FXML
    private val welcomeText: Label? = null
    @FXML
    protected fun onHelloButtonClick() {
        welcomeText!!.text = "Welcome to JavaFX Application!"
    }
}