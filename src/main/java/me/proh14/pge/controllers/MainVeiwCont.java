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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainVeiwCont implements Initializable{

    @FXML
    PasswordField CodeFiled;
    @FXML
    Button CodeMask;
    @FXML
    TextField CodeText;

    ImageView eye = new ImageView(Main.class.getResource("Images/eye.png").toExternalForm());
    ImageView closedEye = new ImageView(Main.class.getResource("Images/closed-eye.png").toExternalForm());


    private boolean masked = false;
    public void onEncryptbtn(ActionEvent e) throws IOException {

        Main.setToEncryptionSecne();
        Main.getStage().centerOnScreen();



    }


    public void onDecryptbtn(ActionEvent e){





    }




    @FXML
    public void onCodeMask(ActionEvent e){


            if(!masked){

                CodeMask.setGraphic(eye);

                CodeFiled.setVisible(false);
                CodeText.setText(CodeFiled.getText());
                CodeText.setVisible(true);
                masked = true;


            }

            else {
                CodeMask.setGraphic(closedEye);
                CodeText.setVisible(false);
                CodeText.setText("");
                CodeFiled.setVisible(true);
                masked = false;
                CodeFiled.selectAll();
            }

    }

    public void onCodeFeild(ActionEvent e){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(Main.getStage());

        try {


        if (e.getSource() == CodeFiled) {
            Main.setKey(Long.parseLong(CodeFiled.getText()));

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("PGE");
            alert.setHeaderText("Done");
            alert.setContentText("The encryption key was successfully set");
            alert.showAndWait();


        } else

            Main.setKey(Long.parseLong(CodeFiled.getText()));

    }
    catch (Exception x){

        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("PGE");
        alert.setHeaderText("Something went wrong...");
        alert.setContentText("Please enter a valid number");
        alert.showAndWait();


    }

    }


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        CodeMask.setGraphic(eye);
        CodeMask.setPrefSize(30,30);
        CodeMask.setFocusTraversable(false);
        CodeMask.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null,null)));
        CodeMask.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        CodeFiled.setTextFormatter(new TextFormatter<> (c -> {


            if (!c.getControlNewText().matches("\\d*")) {

                return null;
            } else if (c.getControlNewText().matches(".{0,15}")) {
                return c;
            }

            return null;

        }));
        CodeText.setTextFormatter(new TextFormatter<> (c -> {


            if (!c.getControlNewText().matches("\\d*")) {

                return null;
            } else if (c.getControlNewText().matches(".{0,15}")) {
                return c;
            }

            return null;

        }));



    }
}