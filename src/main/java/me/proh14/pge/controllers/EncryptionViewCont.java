package me.proh14.pge.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import me.proh14.pge.Main;
import me.proh14.pge.encryptions.AESEncryption;

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

    public void onCopyPaste(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (!isEncrypted) {
            if (clipboard.hasString())
                inputText.setText(clipboard.getString());
        } else {
            ClipboardContent content = new ClipboardContent();
            content.putString(inputText.getText());

            clipboard.setContent(content);
        }
    }

    public void onEncrypt(ActionEvent event) {
        if (!isEncrypted && inputText.getText() != null) {
            undoEncryption.setDisable(false);
            inputText.setDisable(true);
            copyPaste.setText("Copy");
            encrypt.setText("Open Decryptor");
            filePicker.setText("Save to file");
            inputText.setText(AESEncryption.getInstance().encrypt(inputText.getText()));
            isEncrypted = true;
        } else {
            isEncrypted = false;
            Stage stage = (Stage) encrypt.getScene().getWindow();
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
        isEncrypted = false;
        inputText.setText(AESEncryption.getInstance().decrypt(inputText.getText()));
        inputText.setDisable(false);
        encrypt.setText("Encrypt");
        copyPaste.setText("Paste");
        filePicker.setText("Open from file");
        undoEncryption.setDisable(true);
        isEncrypted = false;
    }
}
