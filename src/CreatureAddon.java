import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.scene.paint.Color.rgb;

public class CreatureAddon {

    StackPane pane;
    DBInitialise conn;
    String gId;
    //Text Entries
    DBEntry guid;
    DBEntry pathId;
    DBEntry mount;
    DBEntry bytes1;
    DBEntry bytes2;
    DBEntry emote;
    DBEntry auras;


    public CreatureAddon(StackPane p, String entry, DBInitialise c) {
        pane = p;
        conn = c;
        gId = entry;
        construct();
    }

    private void construct() {
        //Text Entries
        guid = new DBEntry(pane, "NPC GUID: ", gId, 10, 10);
        String statement = "SELECT * FROM creature_addon WHERE guid = " + gId;
        ResultSet rows = conn.processQuery(statement);
        try {
            while (rows.next()) {
                pathId = new DBEntry(pane, "NPC Waypoint Path id: ", Integer.toString(rows.getInt("path_id")), 10, 90);
                mount = new DBEntry(pane, "NPC Mount Model id: ", Integer.toString(rows.getInt("mount")), 10, 150);
                bytes1 = new DBEntry(pane, "NPC Visual Effect (Bytes 1): ", Integer.toString(rows.getInt("bytes1")), 10, 210);
                bytes2 = new DBEntry(pane, "NPC Visual Effect (Bytes 2): ", Integer.toString(rows.getInt("bytes2")), 10, 240);
                emote = new DBEntry(pane, "NPC Emote id: ", Integer.toString(rows.getInt("emote")), 500, 90);
                auras = new DBEntry(pane, "NPC Aura Effects: ", Integer.toString(rows.getInt("bytes1")), 500, 150);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //searchButton
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gId = guid.getVal();
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
                String statement = "UPDATE creature SET " +
                        "guid = " + guid.getVal() + "," +
                        "path_id = " +pathId.getVal() + "," +
                        "mount = " + mount.getVal() + "," +
                        "bytes1 = " + bytes1.getVal() + "," +
                        "bytes2 = " + bytes2.getVal() + "," +
                        "emote = " + emote.getVal() + "," +
                        "auras = " + auras.getVal() + " WHERE guid = " + gId + ";";
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
