package chatowo.ui;

import java.io.IOException;

import chatowo.Chatowo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Chatowo chatowo = new Chatowo();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("ChatOwO");
            Image appIcon = new Image(getClass().getResourceAsStream("/images/icon.png"));
            stage.getIcons().add(appIcon);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatowo(chatowo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
