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

public class CreatureEquipTemplate {

    StackPane pane;
    DBInitialise conn;
    String id;
    //Text Entries
    DBEntry guid;
    DBEntry equipId;
    DBEntry itemEntry[] = new DBEntry[3];


    public CreatureEquipTemplate(StackPane p, String entry, DBInitialise c) {
        pane = p;
        conn = c;
        id = entry;
        construct();
    }

    private void construct() {
        //Text Entries
        guid = new DBEntry(pane, "NPC Entry: ", id, 10, 10);
        String statement = "SELECT * FROM creature_equip_template WHERE entry = " + id;
        ResultSet rows = conn.processQuery(statement);
        try {
            while (rows.next()) {
                equipId = new DBEntry(pane, "Equipment id: ", Integer.toString(rows.getInt("id")), 10, 90);
                for(int i = 0; i < itemEntry.length; i++){
                    itemEntry[i] = new DBEntry(pane, "Item id "+(i+1)+": ", Integer.toString(rows.getInt("itemEntry"+(i+1))), 500, 90+(i*30));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //searchButton
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                id = guid.getVal();
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
                String statement = "UPDATE creature_equip_template SET " +
                        "entry = " + guid.getVal() + "," +
                        "id = " + equipId.getVal() + "," +
                        "itemEntry1 = " + itemEntry[0].getVal() + "," +
                        "itemEntry2 = " + itemEntry[1].getVal() + "," +
                        "itemEntry3 = " + itemEntry[2].getVal() + " WHERE entry = "+id+";";
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