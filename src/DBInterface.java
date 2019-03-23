import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBInterface {
    UserInterface ui;
    Tab[] tab = {
            new Tab("Creature_Template"),
            new Tab("Other adasdsadassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"),
            new Tab("hi"),
            new Tab("Tab"),
            new Tab("hello kevin")
    };

    public DBInterface(UserInterface t){
        ui = t;
        construct();
    }

    public void construct(){
        StackPane mainMenu = new StackPane();
        StackPane subMenu = new StackPane();
        subMenu.setAlignment(Pos.TOP_LEFT);
        TabPane tabs = new TabPane();
        tabs.setMinSize(ui.width, ui.height-tabs.getTranslateY());
        tabs.setMaxSize(ui.width, ui.height-tabs.getTranslateY());
        tabs.getTabs().addAll(tab);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        mainMenu.setAlignment(Pos.TOP_LEFT);
        ui.windowScene = new Scene(mainMenu);
        //BackButton
        Button back = new Button("◄ Main Menu");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                ui.conn.close();
                ui.constructMainInterface();
            }
        });
        back.setId("MenuButton");
        back.setPrefSize(200, 50);
        back.setTextAlignment(TextAlignment.CENTER);
        tabs.setTranslateY(back.getPrefHeight()+5);
        mainMenu.getChildren().add(back);
        //Background
        Rectangle box = new Rectangle();
        box.setTranslateY(tabs.getTranslateY()+tabs.getTabMinHeight());
        box.setId("box");
        box.setWidth(ui.width);
        box.setHeight(ui.height-(tabs.getTranslateY()+tabs.getTabMinHeight()));
        mainMenu.getChildren().add(box);
        mainMenu.getChildren().add(tabs);
        //Tab 0 content
        tab[0].setContent(subMenu);
        //Text box
        String name = "";
        String statement = "SELECT * FROM creature_template WHERE entry = 197";
        ResultSet rows = ui.conn.processQuery(statement);
        try {
            while (rows.next()) {
                name = rows.getString("name");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        Label npcName = new Label("NPC Name: ");
        TextField entry = new TextField(name);
        npcName.setId("label");
        entry.setTranslateY(100);
        npcName.setTranslateY(100);
        entry.setTranslateX(100);
        entry.setMaxWidth(entry.getText().length()*10);
        subMenu.getChildren().addAll(entry, npcName);
        //Background
        mainMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        Image backImg = new Image("CSS/Assets/WoWPortal.jpg");
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
        ui.window.show();
    }

}
