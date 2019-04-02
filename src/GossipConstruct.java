import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class GossipConstruct {
    UserInterface ui;
    TabPane root = new TabPane();
    Tab[] subTab = {
            new Tab("Gossip Menu"),
            new Tab("Gossip Menu Option")
    };
    StackPane[] subTabMenu = new StackPane[subTab.length];
    public GossipConstruct(UserInterface userInterface, Tab tab) {
        ui = userInterface;
        root.setMinSize(ui.width, ui.height-root.getTranslateY());
        root.setMaxSize(ui.width, ui.height-root.getTranslateY());
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        root.getTabs().addAll(subTab);
        tab.setContent(root);
        gossip_menu("4048");
        gossip_menu_option("4048");
    }

    private void gossip_menu(String id){
        subTabMenu[0] = new StackPane();
        subTabMenu[0].setAlignment(Pos.TOP_LEFT);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subTabMenu[0]);
        subTab[0].setContent(scrollPane);
        GossipMenu menu = new GossipMenu(subTabMenu[0], id, ui.conn);
    }

    private void gossip_menu_option(String id){
        subTabMenu[1] = new StackPane();
        subTabMenu[1].setAlignment(Pos.TOP_LEFT);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(subTabMenu[1]);
        subTab[1].setContent(scrollPane);
        GossipMenuOption gossipMenuOption = new GossipMenuOption(subTabMenu[1], id, ui.conn);
    }

}