module com.example.spl1_t2c {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.example.spl1_t2c to javafx.fxml;
    exports com.example.spl1_t2c;
}