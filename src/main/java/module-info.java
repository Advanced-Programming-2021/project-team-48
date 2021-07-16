module project5 {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens sample.controller;
    exports sample.controller;
    opens sample.model;
    exports sample.model;
    opens sample.view.graphic;
    exports sample.view.graphic;
    opens sample;
    exports sample;
}