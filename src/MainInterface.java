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
        Button[] buttons =  new Button[3];
        buttons[0] = new Button("Start WoW\n 5.4.8 Server");
        buttons[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Runtime authServer = Runtime.getRuntime();
                    Runtime worldServer = Runtime.getRuntime();
                    authServer.exec("cmd.exe /c cd \""+"C:\\Users\\chand\\Documents\\Personal\\Mists of Pandaria\\Solution\\bin\\RelWithDebInfo"+"\" & start authserver.exe\"");
                    worldServer.exec("cmd.exe /c cd \""+"C:\\Users\\chand\\Documents\\Personal\\Mists of Pandaria\\Solution\\bin\\RelWithDebInfo"+"\" & start worldserver.exe\"");
                } catch(IOException ex){
                    System.out.println("Executable could not be started.");
                }
            }
        });
        buttons[1] = new Button("Start WoW\n5.4.8 Client");
        buttons[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Runtime game = Runtime.getRuntime();
                    game.exec("D:\\Users\\chand\\Documents\\World of Warcraft Mists of Pandaria\\Wow-64.exe", null, new File("D:\\Users\\chand\\Documents\\World of Warcraft Mists of Pandaria\\"));
                } catch(IOException ex){
                    System.out.println("Executable could not be started.");
                }
            }
        });
        buttons[2] = new Button("Edit WoW\n5.4.8 Database");
        buttons[2].setOnAction(new EventHandler<ActionEvent>() {
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
            buttons[i].setTranslateX(190 + i * (buttons[i].getPrefWidth()+100));
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
        ui.windowScene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                Scale scale = new Scale(ui.windowScene.getWidth()/ui.width, ui.windowScene.getHeight()/ui.height, 0, 0);
                mainMenu.getTransforms().setAll(scale);
            }
        });
        ui.windowScene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                Scale scale = new Scale(ui.windowScene.getWidth()/ui.width, ui.windowScene.getHeight()/ui.height, 0, 0);
                mainMenu.getTransforms().setAll(scale);
            }
        });
        ui.window.maximizedProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Scale scale = new Scale(ui.windowScene.getWidth()/ui.width, ui.windowScene.getHeight()/ui.height, 0, 0);
                mainMenu.getTransforms().setAll(scale);
            }
        });
        //Create Window
        ui.window.setScene(ui.windowScene);
        ui.window.getScene().getStylesheets().add("css/main.css");
        Scale scale = new Scale(ui.windowScene.getWidth()/ui.width, ui.windowScene.getHeight()/ui.height, 0, 0);
        mainMenu.getTransforms().setAll(scale);
        ui.window.setTitle("TrinityDB 5.4.8 Tool");
        ui.window.show();
    }

}
