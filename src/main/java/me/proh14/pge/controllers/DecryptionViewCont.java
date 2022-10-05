package me.proh14.pge.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.proh14.pge.Main;
import me.proh14.pge.encryptions.AESEncryption;

import java.io.*;

public class DecryptionViewCont {

    private final FileChooser fileChooser = new FileChooser();
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
    private final boolean openFile = true;
    private boolean isDecrypted = false;


    public void onFilePicker(ActionEvent event) throws IOException {
        if (openFile) {
            File file = fileChooser.showOpenDialog(Main.getDecryptionScene().getWindow());
            FileReader reader = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();
            int curChar;
            while ((curChar = br.read()) != -1) {
                builder.append((char) curChar);
            }

            br.close();
            reader.close();
            inputText.setText(builder.toString());
        } else {
            File file = fileChooser.showSaveDialog(Main.getDecryptionScene().getWindow());
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter wr = new BufferedWriter(writer);
            int curChar;
            String ourString = inputText.getText();

            wr.write(ourString);


            wr.close();
            writer.close();
        }
    }

    public void onDecrypt(ActionEvent event) {
        if (!isDecrypted && inputText.getText() != null) {
            undoDecryption.setDisable(false);
            inputText.setDisable(true);
            copyPaste.setText("Copy");
            decrypt.setText("Open Encryptor");
            filePicker.setText("Save to file");
            inputText.setText(AESEncryption.getInstance().decrypt(inputText.getText()));
            isDecrypted = true;
        } else {
            Stage stage = (Stage) decrypt.getScene().getWindow();
            stage.setScene(Main.getEncryptionScene());
            stage.setTitle("PGE - Encryptor");
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

    public void onCopyPaste(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (!isDecrypted) {
            if (clipboard.hasString())
                inputText.setText(clipboard.getString());
        } else {
            ClipboardContent content = new ClipboardContent();
            content.putString(inputText.getText());

            clipboard.setContent(content);
        }
    }

    public void onUndoDecryption(ActionEvent event) {
        isDecrypted = false;
        inputText.setText(AESEncryption.getInstance().encrypt(inputText.getText()));
        inputText.setDisable(false);
        decrypt.setText("Decrypt");
        copyPaste.setText("Paste");
        filePicker.setText("Open from file");
        undoDecryption.setDisable(true);
        isDecrypted = false;
        openFile = true;
    }

    public void onClose(ActionEvent event) {
        Main.setToMainScene();
    }
}
