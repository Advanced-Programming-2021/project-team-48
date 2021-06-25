module you.gay.oh {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens sample.view.graphic;
    exports sample.view.graphic;
}