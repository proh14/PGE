package me.proh14.pge.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import me.proh14.pge.Main;
import me.proh14.pge.encryptions.XOREncryption;

public class EncryptionViewCont {

    @FXML
    public Button encrypt;
    @FXML
    public Button copyPaste;
    @FXML
    public Button filePicker;
    @FXML
    public TextArea inputText;
    @FXML
    public MenuItem undoEncryption;


    private boolean isEncrypted = false;

    public void onPaste(ActionEvent event) {



    }

    public void onEncrypt(ActionEvent event) {

        if(!isEncrypted && inputText.getText() != null){
        undoEncryption.setDisable(false);
        inputText.setDisable(true);
        copyPaste.setText("Copy");
        encrypt.setText("Open Decryptor");
        filePicker.setText("Save to file");
        inputText.setText(XOREncryption.getInstance().encryptDecrypt(inputText.getText()));
        isEncrypted = true;
        }
        else {
            isEncrypted = false;
            Stage stage = (Stage)encrypt.getScene().getWindow();
            stage.setTitle("PGE - Decryptor");
            stage.setScene(Main.getDecryptionScene());
            stage.sizeToScene();
            stage.centerOnScreen();
            inputText.setDisable(false);
            inputText.clear();
            encrypt.setText("Encrypt");
            copyPaste.setText("Paste");
            filePicker.setText("Open from file");
        }

    }

    public void onFilePicker(ActionEvent event) {
    }


    public void onClose(ActionEvent event) {
        Main.setToMainScene();
    }

    public void onUndoEncryption(ActionEvent event) {
        inputText.setText(XOREncryption.getInstance().encryptDecrypt(inputText.getText()));
        inputText.setDisable(false);
        encrypt.setText("Encrypt");
        copyPaste.setText("Paste");
        filePicker.setText("Open from file");
        undoEncryption.setDisable(true);

    }
}
