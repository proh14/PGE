package me.proh14.pge.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import me.proh14.pge.Main;
import me.proh14.pge.encryptions.XOREncryption;

public class DecryptionViewCont {

    @FXML
    public Button copyPaste;
    @FXML
    public Button decrypt;
    @FXML
    public TextArea inputText;
    @FXML
    public MenuItem undoDecryption;
    @FXML
    public Button filePicker;

    private boolean isDecrypted = false;


    public void onFilePicker(ActionEvent event) {
    }

    public void onDecrypt(ActionEvent event) {
        if (!isDecrypted && inputText.getText() != null) {
            undoDecryption.setDisable(false);
            inputText.setDisable(true);
            copyPaste.setText("Copy");
            decrypt.setText("Open Encryptor");
            filePicker.setText("Save to file");
            inputText.setText(XOREncryption.getInstance().encryptDecrypt(inputText.getText()));
            isDecrypted = true;
        } else {
            Stage stage = (Stage) decrypt.getScene().getWindow();
            stage.setScene(Main.getDecryptionScene());
            stage.setTitle("PGE - Decryptor");
            stage.sizeToScene();
            stage.centerOnScreen();
            inputText.setDisable(false);
            inputText.clear();
            decrypt.setText("Decrypt");
            copyPaste.setText("Paste");
            filePicker.setText("Open from file");
            isDecrypted = false;
        }
    }

    public void onPaste(ActionEvent event) {
    }

    public void onUndoDecryption(ActionEvent event) {
        inputText.setText(XOREncryption.getInstance().encryptDecrypt(inputText.getText()));
        inputText.setDisable(false);
        decrypt.setText("Decrypt");
        copyPaste.setText("Paste");
        filePicker.setText("Open from file");
        undoDecryption.setDisable(true);
    }

    public void onClose(ActionEvent event) {
        Main.setToMainScene();
    }
}
