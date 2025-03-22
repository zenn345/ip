package zenn.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zenn.Zenn;
import zenn.ui.Ui;

/**
 * A GUI for Zenn Bot using FXML.
 */
public class Main extends Application {
    private Zenn zenn = new Zenn();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Zenn Bot");
            fxmlLoader.<MainWindow>getController().showMessage(Ui.showWelcome());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setZenn(zenn);  // inject the Zenn Bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}