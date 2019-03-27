import com.sun.jmx.snmp.SnmpUnsignedInt;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
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

import static javafx.scene.paint.Color.rgb;

public class DBInterface {
    UserInterface ui;
    TabPane tabs = new TabPane();
    Tab[] tab = {
            new Tab("Creature Template"),
            new Tab("Creature")
    };
    StackPane mainMenu = new StackPane();
    StackPane[] subMenu = new StackPane[tab.length];
    public DBInterface(UserInterface t){
        ui = t;
        construct();
    }

    public void construct(){
        //Tab panels
        tabs.setMinSize(ui.width, ui.height-tabs.getTranslateY());
        tabs.setMaxSize(ui.width, ui.height-tabs.getTranslateY());
        tabs.getTabs().addAll(tab);
        //Default tab values on first start up.
        creature_template("197");
        creature("168289");
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
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
        back.setTranslateX(-2);
        tabs.setTranslateY(back.getPrefHeight()+2);
        //Box Background
        Rectangle box = new Rectangle();
        box.setTranslateY(tabs.getTranslateY());
        box.setId("box");
        box.setWidth(ui.width);
        box.setHeight(ui.height-tabs.getTranslateY());
        //Main Menu
        mainMenu.setAlignment(Pos.TOP_LEFT);
        mainMenu.getChildren().add(back);
        mainMenu.getChildren().add(box);
        mainMenu.getChildren().add(tabs);
        //Window Scene
        ui.windowScene = new Scene(mainMenu);
        //Background
        mainMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        Image backImg = new Image("CSS/Assets/quest.jpg");
        ImagePattern pattern = new ImagePattern(backImg);
        ui.windowScene.setFill(pattern);
        //Scale to window handler
        mainMenu.getTransforms().add(new Scale(ui.height/ui.height, ui.width/ui.width, 0, 0));
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
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Scale scale = new Scale(ui.windowScene.getWidth()/ui.width, ui.windowScene.getHeight()/ui.height, 0, 0);
                mainMenu.getTransforms().setAll(scale);
            }
        });
        //Create Window
        ui.window.setScene(ui.windowScene);
        ui.window.getScene().getStylesheets().add("css/main.css");
        Scale scale = new Scale(ui.windowScene.getWidth()/ui.width, ui.windowScene.getHeight()/ui.height, 0, 0);
        mainMenu.getTransforms().setAll(scale);
    }

    private void creature_template(String id){
        subMenu[0] = new StackPane();
        subMenu[0].setAlignment(Pos.TOP_LEFT);
        subMenu[0].setPrefHeight(1500);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subMenu[0]);
        tab[0].setContent(scrollPane);
        CreatureTemplate template = new CreatureTemplate(subMenu[0], id, ui.conn);
    }

    private void creature(String guid){
        subMenu[1] = new StackPane();
        subMenu[1].setAlignment(Pos.TOP_LEFT);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subMenu[1]);
        tab[1].setContent(scrollPane);
        Creature template = new Creature(subMenu[1], guid, ui.conn);
    }

}
