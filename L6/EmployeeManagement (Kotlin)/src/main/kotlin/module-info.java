module com.example.employeemanagement {
    requires javafx.controls;
    requires javafx.fxml;
                requires kotlin.stdlib;
    
                            
    opens com.example.employeemanagement to javafx.fxml;
    exports com.example.employeemanagement;
}