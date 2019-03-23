import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.*;

public class Launchpad extends Application {
    UserInterface ui;

    public static void main(String args[]) {
        launch(args);
        Launchpad main = new Launchpad();
    }

    public Launchpad() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui = new UserInterface(primaryStage);
    }
}
