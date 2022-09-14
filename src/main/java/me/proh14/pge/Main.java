package me.proh14.pge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Scene mainScene;
    private static Scene encryptionScene;
    private static Scene decryptionScene;

    private static Stage stage;

    public static void setToMainScene() {
        stage.setScene(mainScene);
        stage.centerOnScreen();
        stage.sizeToScene();
        stage.setTitle("PGE");
    }

    public static Scene getDecryptionScene() {

        return decryptionScene;

    }

    public static Scene getEncryptionScene() {

        return encryptionScene;

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("FXMLFiles/MainView.fxml"));
        Scene scene = new Scene(mainLoader.load());
        Main.mainScene = scene;
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("Images/ApplicationIcons/50x50.png")).toExternalForm()));
        stage.setTitle("PGE");
        FXMLLoader decryptionLoader = new FXMLLoader(getClass().getResource("FXMLFiles/DecryptionView.fxml"));
        FXMLLoader encryptionLoader = new FXMLLoader(getClass().getResource("FXMLFiles/EncryptionView.fxml"));
        Main.decryptionScene = new Scene(decryptionLoader.load());
        Main.encryptionScene = new Scene(encryptionLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}