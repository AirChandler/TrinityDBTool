import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import java.io.File;
import java.io.IOException;

public class MainInterface {
    UserInterface ui;

    public MainInterface(UserInterface t){
        ui = t;
        construct();
    }

    public void construct(){
        //Main Menu elements
        StackPane mainMenu = new StackPane();
        Button[] buttons =  new Button[2];
        buttons[0] = new Button("Start WoW\n5.4.8 Client");
        buttons[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Runtime.getRuntime().exec("D:\\Users\\chand\\Documents\\World of Warcraft Mists of Pandaria\\Wow-64.exe", null, new File("D:\\Users\\chand\\Documents\\World of Warcraft Mists of Pandaria\\"));
                } catch(IOException ex){
                    System.out.println("Executable could not be started.");
                }
            }
        });
        buttons[1] = new Button("Edit WoW\n5.4.8 Database");
        buttons[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ui.conn = new DBInitialise();
                ui.constructDBInterface();
            }
        });
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setTextAlignment(TextAlignment.CENTER);
            buttons[i].setId("button");
            buttons[i].setPrefSize(250, 150);
            buttons[i].setTranslateX(340 + i * (buttons[i].getPrefWidth()+100));
            buttons[i].setTranslateY(ui.height/2-(buttons[i].getPrefHeight()/2));
        }
        mainMenu.getChildren().addAll(buttons);
        mainMenu.setAlignment(Pos.TOP_LEFT);
        mainMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        ui.windowScene = new Scene(mainMenu);
        //Background
        Image backImg = new Image("CSS/Assets/WoWMap.jpg");
        ImagePattern pattern = new ImagePattern(backImg);
        ui.windowScene.setFill(pattern);
        //Scale to window handler
        mainMenu.getTransforms().add(new Scale(ui.height/ui.height, ui.width/ui.width, 0, 0));
        ui.windowScene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                Scale scale = new Scale((double)newSceneWidth/ui.width, ui.window.getHeight()/ui.height, 0, 0);
                mainMenu.getTransforms().setAll(scale);
            }
        });
        ui.windowScene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                Scale scale = new Scale(ui.window.getWidth()/ui.width, (double)newSceneHeight/ui.height, 0, 0);
                mainMenu.getTransforms().setAll(scale);
            }
        });
        //Create Window
        ui.window.setScene(ui.windowScene);
        ui.window.getScene().getStylesheets().add("css/main.css");
        ui.window.setTitle("TrinityDB 5.4.8 Tool");
        ui.window.show();
    }

}
