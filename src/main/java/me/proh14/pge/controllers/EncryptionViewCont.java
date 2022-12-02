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

public class EncryptionViewCont {

    private final FileChooser fileChooser = new FileChooser();
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
    private boolean openFile = true;
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
            openFile = false;
        } else {
            isEncrypted = false;
            openFile = true;
            inputText.setDisable(false);
            inputText.clear();
            encrypt.setText("Encrypt");
            copyPaste.setText("Paste");
            filePicker.setText("Open from file");

            Stage stage = (Stage) encrypt.getScene().getWindow();
            stage.setTitle("PGE - Decryptor");
            stage.setScene(Main.getDecryptionScene());
            stage.sizeToScene();
            stage.centerOnScreen();
        }

    }

    public void onFilePicker(ActionEvent event) throws IOException {

        if (openFile) {
            File file = fileChooser.showOpenDialog(Main.getDecryptionScene().getWindow());
            if (file == null)
                return;
            FileReader reader = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
                builder.append(line).append('\n');

            br.close();
            reader.close();
            inputText.setText(builder.toString());
        } else {
            File file = fileChooser.showSaveDialog(Main.getDecryptionScene().getWindow());
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter wr = new BufferedWriter(writer);
            String ourString = inputText.getText();

            wr.write(ourString);

            wr.close();
            writer.close();
        }

    }


    public void onClose(ActionEvent event) {
//        isEncrypted = false;
//        openFile = true;
//        inputText.setDisable(false);
//        inputText.clear();
//        encrypt.setText("Encrypt");
//        copyPaste.setText("Paste");
//        filePicker.setText("Open from file");

        Main.setToMainScene();
    }

    public void onUndoEncryption(ActionEvent event) {
        isEncrypted = false;
        openFile = true;
        inputText.setText(AESEncryption.getInstance().decrypt(inputText.getText()));
        undoEncryption.setDisable(true);
        inputText.setDisable(false);
        encrypt.setText("Encrypt");
        copyPaste.setText("Paste");
        filePicker.setText("Open from file");
    }
}
