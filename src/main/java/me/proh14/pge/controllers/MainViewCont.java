package me.proh14.pge.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.proh14.pge.Main;
import me.proh14.pge.encryptions.AESEncryption;
import me.proh14.pge.encryptions.XOREncryption;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainViewCont implements Initializable {

    private final ImageView eye = new ImageView(Objects.requireNonNull(getClass().getResource("/me/proh14/pge/Images/eye.png")).toExternalForm());
    private final ImageView closedEye = new ImageView(Objects.requireNonNull(getClass().getResource("/me/proh14/pge/Images/closed-eye.png")).toExternalForm());
    @FXML
    private VBox root;
    @FXML
    private PasswordField codeFiled;
    @FXML
    private ToggleButton codeMask;
    @FXML
    private TextField codeText;

    public void onEncryptBtn(ActionEvent e) {
        if ((codeMask.isSelected() && codeText.getText().isEmpty()) || (!codeMask.isSelected() && codeFiled.getText().isEmpty())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "key is empty", ButtonType.OK);
            errorAlert.initOwner(root.getScene().getWindow());
            errorAlert.show();
            return;
        }

        if (codeMask.isSelected())
            AESEncryption.getInstance().setKey(codeText.getText());
        else
            AESEncryption.getInstance().setKey(codeFiled.getText());
        System.out.println(AESEncryption.getInstance().getKey());
        if (Main.getEncryptionScene() != null) {
            Stage stage = ((Stage) root.getScene().getWindow());
            stage.setScene(Main.getEncryptionScene());
            stage.setTitle("PGE - Encryptor");
            stage.centerOnScreen();
            stage.sizeToScene();
        }
    }

    public void onDecryptBtn(ActionEvent e) {
        if ((codeMask.isSelected() && codeText.getText().isEmpty()) || (!codeMask.isSelected() && codeFiled.getText().isEmpty())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "key is empty", ButtonType.OK);
            errorAlert.initOwner(root.getScene().getWindow());
            errorAlert.show();
            return;
        }

        if (codeMask.isSelected())
            XOREncryption.getInstance().setKey(Long.parseLong(codeText.getText()));
        else
            XOREncryption.getInstance().setKey(Long.parseLong(codeFiled.getText()));
        System.out.println(XOREncryption.getInstance().getKey());
        if (Main.getEncryptionScene() != null) {
            Stage stage = ((Stage) root.getScene().getWindow());
            stage.setTitle("PGE - Decryptor");
            stage.setScene(Main.getEncryptionScene());
            stage.centerOnScreen();
            stage.sizeToScene();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeMask.setGraphic(closedEye);
        codeMask.setPrefSize(30, 40);
        codeMask.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        codeMask.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        codeMask.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                codeMask.setGraphic(eye);
                codeFiled.setVisible(false);
                if (!codeFiled.getText().isEmpty())
                    codeText.setText(codeFiled.getText());
                codeText.setVisible(true);
            } else {
                codeMask.setGraphic(closedEye);
                codeText.setVisible(false);
                if (!codeText.getText().isEmpty())
                    codeFiled.setText(codeText.getText());
                codeFiled.setVisible(true);
            }
        });


    }

}