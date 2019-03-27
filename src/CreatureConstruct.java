import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class CreatureConstruct {
    UserInterface ui;
    TabPane root = new TabPane();
    Tab[] subTab = {
            new Tab("Creature"),
            new Tab("Creature Addon"),
            new Tab("Creature Class Level Stats"),
            new Tab("Creature Template")
    };
    StackPane[] subTabMenu = new StackPane[subTab.length];
    public CreatureConstruct(UserInterface userInterface, Tab tab) {
        ui = userInterface;
        root.setMinSize(ui.width, ui.height-root.getTranslateY());
        root.setMaxSize(ui.width, ui.height-root.getTranslateY());
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        root.getTabs().addAll(subTab);
        tab.setContent(root);
        creature("168289");
        creature_addon("17");
        creature_classlvlstats("1", "1");
        creature_template("197");
    }

    private void creature(String guid){
        subTabMenu[0] = new StackPane();
        subTabMenu[0].setAlignment(Pos.TOP_LEFT);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subTabMenu[0]);
        subTab[0].setContent(scrollPane);
        Creature template = new Creature(subTabMenu[0], guid, ui.conn);
    }

    private void creature_addon(String guid){
        subTabMenu[1] = new StackPane();
        subTabMenu[1].setAlignment(Pos.TOP_LEFT);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subTabMenu[1]);
        subTab[1].setContent(scrollPane);
        CreatureAddon creatureAddon = new CreatureAddon(subTabMenu[1], guid, ui.conn);
    }

    private void creature_classlvlstats(String level, String classType){
        subTabMenu[2] = new StackPane();
        subTabMenu[2].setAlignment(Pos.TOP_LEFT);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subTabMenu[2]);
        subTab[2].setContent(scrollPane);
        CreatureClassLevelStats creatureClassLevelStats = new CreatureClassLevelStats(subTabMenu[2], level, classType, ui.conn);
    }

    private void creature_template(String id){
        subTabMenu[3] = new StackPane();
        subTabMenu[3].setAlignment(Pos.TOP_LEFT);
        subTabMenu[3].setPrefHeight(1550);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subTabMenu[3]);
        subTab[3].setContent(scrollPane);
        CreatureTemplate template = new CreatureTemplate(subTabMenu[3], id, ui.conn);
    }

}
