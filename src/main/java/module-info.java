module me.proh14.pge {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.proh14.pge to javafx.fxml;
    exports me.proh14.pge;
    exports me.proh14.pge.encryptions;
    exports me.proh14.pge.controllers;
    opens me.proh14.pge.controllers to javafx.fxml;
}