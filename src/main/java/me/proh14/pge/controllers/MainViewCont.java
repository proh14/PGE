package me.proh14.pge.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import me.proh14.pge.Main;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainViewCont implements Initializable {

    @FXML
    private PasswordField codeFiled;
    @FXML
    private ToggleButton codeMask;
    @FXML
    private TextField codeText;

    private final ImageView eye = new ImageView(Objects.requireNonNull(getClass().getResource("/me/proh14/pge/Images/eye.png")).toExternalForm());

    private final ImageView closedEye = new ImageView(Objects.requireNonNull(getClass().getResource("/me/proh14/pge/Images/closed-eye.png")).toExternalForm());

    public void onEncryptBtn(ActionEvent e) {
        Main.setToEncryptionScene();
    }

    public void onDecryptBtn(ActionEvent e) {


    }

    public void onCodeField(ActionEvent e) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(Main.getStage());

        try {
            if (e.getSource() == codeFiled) {
                Main.setKey(Long.parseLong(codeFiled.getText()));

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("PGE");
                alert.setHeaderText("Done");
                alert.setContentText("The encryption key was successfully set");
                alert.showAndWait();
            } else {
                Main.setKey(Long.parseLong(codeFiled.getText()));
            }
        } catch (Exception x) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("PGE");
            alert.setHeaderText("Something went wrong...");
            alert.setContentText("Please enter a valid number");
            alert.showAndWait();
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

        codeFiled.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*"))
                return null;
            else if (c.getControlNewText().matches(".{0,15}"))
                return c;

            return null;

        }));

        codeText.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*"))
                return null;
            else if (c.getControlNewText().matches(".{0,15}"))
                return c;

            return null;

        }));
    }
}