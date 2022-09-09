package me.proh14.pge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static Scene encryptionScene;

    private static long key = 0;

    private static Scene mainScene;

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader encryptloader = new FXMLLoader(getClass().getResource("FXMLFiles/EncryptionView.fxml"));
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("FXMLFiles/MainView.fxml"));
        Parent root = mainLoader.load();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("Images/ApplicationIcons/50x50.png")).toExternalForm()));
        stage.setTitle("PGE");

        stage.setScene(scene);
        stage.show();

        this.mainScene = scene;
        this.stage = stage;
        this.encryptionScene = new Scene(encryptloader.load());

    }

    public static void setKey(long l) {

        key = l;

    }

    public static long getKey(long l) {

        return key;

    }

    public static Stage getStage() {
        return stage;
    }


    public static void setToEncryptionScene() {
        stage.setScene(encryptionScene);
        stage.sizeToScene();
    }

    public static void setToMainScene() {
        stage.setScene(mainScene);
        stage.sizeToScene();
    }


    public static void main(String[] args) {
        launch();
    }


}