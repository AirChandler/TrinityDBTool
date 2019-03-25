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
        String subName = "";
        String iconName = "";
        String dungeonDiff[] = new String[3];
        String killCr[] = new String[2];
        String modelId[] = new String[4];
        String gossipMenuId = "";
        String minLevel = "";
        String maxLevel = "";
        String exp = "";
        String expUnk = "";
        String factionA = "";
        String factionH = "";
        String npcFlag = "";
        String walkSpeed = "";
        String runSpeed = "";
        String scale = "";
        String rank = "";
        String minDmg = "";
        String maxDmg = "";
        String dmgSchool = "";
        String statement = "SELECT * FROM creature_template WHERE entry = " + id;
        ResultSet rows = ui.conn.processQuery(statement);
        try {
            while (rows.next()) {
                name = rows.getString("name");
                for(int i = 0; i < 3; i++){
                    dungeonDiff[i] = Integer.toString(rows.getInt("Difficulty_Entry_" + (i+1)));
                }
                for(int i = 0; i < 2; i++){
                    killCr[i] = Integer.toString(rows.getInt("KillCredit"+(i+1)));
                }
                for(int i = 0; i < 4; i++){
                    modelId[i] = Integer.toString(rows.getInt("modelid"+(i+1)));
                }
                subName = rows.getString("subname");
                iconName = rows.getString("IconName");
                gossipMenuId = Integer.toString(rows.getInt("gossip_menu_id"));
                minLevel = Integer.toString((rows.getInt("minlevel")));
                maxLevel = Integer.toString((rows.getInt("maxlevel")));
                exp = Integer.toString((rows.getInt("exp")));
                expUnk = Integer.toString((rows.getInt("exp_unk")));
                factionA = Integer.toString((rows.getInt("faction_A")));
                factionH = Integer.toString((rows.getInt("faction_H")));
                npcFlag = Integer.toString((rows.getInt("npcflag")));
                walkSpeed = Float.toString((rows.getFloat("speed_walk")));
                runSpeed = Float.toString((rows.getFloat("speed_run")));
                scale = Float.toString((rows.getFloat("scale")));
                rank = Integer.toString((rows.getInt("rank")));
                minDmg = Float.toString((rows.getFloat("mindmg")));
                maxDmg = Float.toString((rows.getFloat("maxdmg")));
                dmgSchool = Integer.toString((rows.getInt("dmgschool")));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        DBEntry entry = new DBEntry(subMenu[0], "NPC Entry ID: ", id, 10, 10);
        DBEntry npcName = new DBEntry(subMenu[0], "NPC Name: ", name, 10, 90);
        DBEntry sName = new DBEntry(subMenu[0], "Sub Name (<> prefix): ", subName, 10, 120);
        DBEntry iName = new DBEntry(subMenu[0], "(Cursor) Icon Type: ", iconName, 10, 150);
        DBEntry[] dungDiff = new DBEntry[3];
        for(int i = 0; i < 3; i++){
            dungDiff[i] = new DBEntry(subMenu[0], "Dungeon Difficulty Entry " + (i+1)+": ", dungeonDiff[i], 10, 200+(i*30));
        }
        DBEntry[] killCredit = new DBEntry[2];
        for(int i = 0; i < 2; i++){
            killCredit[i] = new DBEntry(subMenu[0], "Quest Kill Credit Entry " + (i+1) +": ", killCr[i], 10, 300+(i*30));
        }
        DBEntry[] modelIds = new DBEntry[4];
        for(int i = 0; i < 4; i++){
            modelIds[i] = new DBEntry(subMenu[0], "Model id " + (i+1) +": ", modelId[i], 10, 400+(i*30));
        }
        DBEntry gossip = new DBEntry(subMenu[0], "Gossip Menu id: ", gossipMenuId, 500, 90);
        DBEntry minLvl = new DBEntry(subMenu[0], "Min Level: ", minLevel, 500, 140);
        DBEntry maxLvl = new DBEntry(subMenu[0], "Max Level: ", maxLevel, 500, 170);
        DBEntry hpTab = new DBEntry(subMenu[0], "HP Table (exp): ", exp, 500, 240);
        DBEntry hpTabUnk = new DBEntry(subMenu[0], "HP Table (exp_unk): ", expUnk, 500, 270);
        DBEntry facA = new DBEntry(subMenu[0], "Faction Alliance: ", factionA, 500, 330);
        DBEntry facH = new DBEntry(subMenu[0], "Faction Horde: ", factionH, 500, 360);
        DBEntry npcF = new DBEntry(subMenu[0], "NPC Type Flag: ", npcFlag, 500, 420);
        DBEntry speedW = new DBEntry(subMenu[0], "Walk Speed: ", walkSpeed, 900, 360);
        DBEntry speedR = new DBEntry(subMenu[0], "Run Speed: ", runSpeed, 900, 390);
        DBEntry npcS = new DBEntry(subMenu[0], "NPC Scale Size: ", scale, 500, 480);
        DBEntry npcRank = new DBEntry(subMenu[0], "NPC Power Rank: ", rank, 900, 450);
        DBEntry minDamage = new DBEntry(subMenu[0], "Min Damage: ", minDmg, 900, 510);
        DBEntry maxDamage = new DBEntry(subMenu[0], "Max Damage: ", minDmg, 900, 540);
        DBEntry dmgSchoolType = new DBEntry(subMenu[0], "NPC Damage School Type: ", dmgSchool, 10, 540);
        //NPC Model
        Rectangle npcModel = new Rectangle();
        npcModel.setWidth(300);
        npcModel.setHeight(300);
        try {
            String subDir = Character.toString(modelId[0].charAt(0));
            String imgUrl = "http://mop-static.tauri.hu/npc/" + subDir + "/creature-display-" + modelId[0] + ".jpg";
            Image backImg = new Image(imgUrl);
            ImagePattern pattern = new ImagePattern(backImg);
            npcModel.setFill(pattern);
        } catch (Exception ex){
            npcModel.setVisible(false);
        }
        npcModel.setTranslateX(1250-npcModel.getWidth());
        npcModel.setTranslateY(10);
        //Setting the height and width of the arc
        npcModel.setArcWidth(50.0);
        npcModel.setArcHeight(50.0);
        npcModel.setStroke(rgb(112, 63, 1));
        npcModel.setStrokeWidth(5);
        subMenu[0].getChildren().add(npcModel);
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
