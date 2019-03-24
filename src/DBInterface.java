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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBInterface {
    UserInterface ui;
    boolean scaleW = false;
    boolean scaleH = false;
    TabPane tabs = new TabPane();
    Tab[] tab = {
            new Tab("NPC"),
            new Tab("Quest")
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
        creature_template("197");
        tabs.getTabs().addAll(tab);
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
        back.setTextAlignment(TextAlignment.CENTER);
        tabs.setTranslateY(back.getPrefHeight()+5);
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
        subMenu[0].setPrefSize(1920, 1080);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subMenu[0]);
        tab[0].setContent(scrollPane);
        //Text box
        String name = "";
        String dungeonDiff[] = new String[3];
        String statement = "SELECT * FROM creature_template WHERE entry = " + id;
        ResultSet rows = ui.conn.processQuery(statement);
        try {
            while (rows.next()) {
                name = rows.getString("name");
                for(int i = 0; i < 3; i++){
                    dungeonDiff[i] = Integer.toString(rows.getInt("Difficulty_Entry_" + (i+1)));
                }
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        DBEntry entry = new DBEntry(subMenu[0], "NPC Entry ID: ", id, 100, 10);
        DBEntry npcName = new DBEntry(subMenu[0], "NPC Name: ", name, 100, 900);
        DBEntry[] dungDiff = new DBEntry[3];
        for(int i = 0; i < 3; i++){
            dungDiff[i] = new DBEntry(subMenu[0], "Dungeon Difficulty Entry: " + (i+1), dungeonDiff[i], 100, 200+(i*100));
        }
        //updateButton
        Button update = new Button("Search");
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
               creature_template(entry.getID());
            }
        });
        update.setId("SubMenuButton");
        update.setPrefSize(100, 30);
        update.setTextAlignment(TextAlignment.CENTER);
        update.setTranslateY(8);
        update.setTranslateX(350);
        subMenu[0].getChildren().add(update);
    }

}
