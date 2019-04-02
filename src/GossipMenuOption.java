import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GossipMenuOption {
    StackPane pane;
    DBInitialise conn;
    String id;
    //Text Entries
    DBEntry entry;
    DBEntry gossipId;
    DBEntry optionIcon;
    DBEntry optionText;
    DBEntry optionId;
    DBEntry npcOptionFlag;
    DBEntry actionMenuId;
    DBEntry poiId;
    DBEntry boxCoded;
    DBEntry boxMoney;
    DBEntry boxText;

    public GossipMenuOption(StackPane p, String entry, DBInitialise c) {
        pane = p;
        conn = c;
        id = entry;
        construct();
    }

    private void construct() {
        //Text Entries
        entry = new DBEntry(pane, "Gossip Entry: ", id, 10, 10);
        String statement = "SELECT * FROM gossip_menu_option WHERE menu_id = " + id;
        ResultSet rows = conn.processQuery(statement);
        try {
            while (rows.next()) {
                gossipId = new DBEntry(pane, "Gossip id: ", Integer.toString(rows.getInt("text_id")), 10, 90);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //searchButton
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                id = entry.getVal();
                construct();
            }
        });
        search.setId("SubMenuButton");
        search.setPrefSize(100, 30);
        search.setTextAlignment(TextAlignment.CENTER);
        search.setTranslateY(8);
        search.setTranslateX(300);
        pane.getChildren().add(search);
        //updateButton
        Button update = new Button("Update");
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String statement = "UPDATE gossip_menu SET " +
                        "entry = " + entry.getVal() + "," +
                        "text_id = \"" + gossipId.getVal() + "\" WHERE entry = " + id + ";";
                conn.processUpdate(statement);
            }
        });
        update.setId("SubMenuButton");
        update.setPrefSize(100, 30);
        update.setTextAlignment(TextAlignment.CENTER);
        update.setTranslateY(8);
        update.setTranslateX(450);
        pane.getChildren().add(update);
    }
}
