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
        creature_template("197");
        creature("242141");
    }
    private void creature_template(String id){
        subTabMenu[0] = new StackPane();
        subTabMenu[0].setAlignment(Pos.TOP_LEFT);
        subTabMenu[0].setPrefHeight(1500);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subTabMenu[0]);
        subTab[0].setContent(scrollPane);
        CreatureTemplate template = new CreatureTemplate(subTabMenu[0], id, ui.conn);
    }

    private void creature(String guid){
        subTabMenu[1] = new StackPane();
        subTabMenu[1].setAlignment(Pos.TOP_LEFT);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subTabMenu[1]);
        subTab[1].setContent(scrollPane);
        Creature template = new Creature(subTabMenu[1], guid, ui.conn);
    }

}
