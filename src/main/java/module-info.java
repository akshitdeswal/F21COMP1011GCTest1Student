module com.example.f21comp1011gctest1student {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.f21comp1011gctest1student to javafx.fxml;
    exports com.example.f21comp1011gctest1student;
}