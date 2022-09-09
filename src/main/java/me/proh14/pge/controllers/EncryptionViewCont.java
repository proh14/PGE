package me.proh14.pge.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import me.proh14.pge.Main;

public class EncryptionViewCont {

    @FXML
    public Button encrypt;

    @FXML
    public Button paste;

    @FXML
    public Button filePicker;

    public void onPaste(ActionEvent event) {

    }

    public void onEncrypt(ActionEvent event) {

    }

    public void onFilePicker(ActionEvent event) {

    }

    public void onClose(ActionEvent event) {
        Main.setToMainScene();
    }
}
