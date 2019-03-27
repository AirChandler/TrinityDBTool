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

public class Creature {

    StackPane pane;
    DBInitialise conn;
    String gId;
    //Text Entries
    DBEntry guid;
    DBEntry id;
    DBEntry map;
    DBEntry spawnMask;
    DBEntry phaseMask;
    DBEntry modelId;
    DBEntry equipmentId;
    DBEntry posX;
    DBEntry posY;
    DBEntry posZ;
    DBEntry orientation;
    DBEntry spawnTime;
    DBEntry spawnDist;
    DBEntry cWayPoint;
    DBEntry cHp;
    DBEntry cMana;
    DBEntry moveType;
    DBEntry npcFlag;
    DBEntry unitFlags;
    DBEntry dynFlags;

    public Creature(StackPane p, String entry, DBInitialise c){
        pane = p;
        conn = c;
        gId = entry;
        construct();
    }

    private void construct(){
        //Text Entries
        guid = new DBEntry(pane, "NPC GUID: ", gId, 10, 10);
        String statement = "SELECT * FROM creature WHERE guid = " + gId;
        ResultSet rows = conn.processQuery(statement);
        try {
            while (rows.next()) {
                id = new DBEntry(pane, "NPC Entry id: ", Integer.toString(rows.getInt("id")), 10, 90);
                map = new DBEntry(pane, "Map: ", Integer.toString(rows.getInt("map")), 10, 150);
                spawnMask = new DBEntry(pane, "Spawn Mask: ", Integer.toString(rows.getInt("spawnMask")), 10, 210);
                phaseMask = new DBEntry(pane, "Phase Mask: ", Integer.toString(rows.getInt("phaseMask")), 10, 240);
                modelId = new DBEntry(pane, "Model id: ", Integer.toString(rows.getInt("modelid")), 10, 300);
                equipmentId = new DBEntry(pane, "Equipment id: ", Integer.toString(rows.getInt("equipment_id")), 10, 330);
                posX = new DBEntry(pane, "Position X: ", Float.toString(rows.getFloat("position_x")), 500, 90);
                posY = new DBEntry(pane, "Position Y: ", Float.toString(rows.getFloat("position_y")), 500, 120);
                posZ = new DBEntry(pane, "Position Z: ", Float.toString(rows.getFloat("position_z")), 500, 150);
                orientation = new DBEntry(pane, "Orientation: ", Float.toString(rows.getFloat("orientation")), 500, 180);
                spawnTime = new DBEntry(pane, "Spawn Time (sec): ", Integer.toString(rows.getInt("spawntimesecs")), 500, 240);
                spawnDist = new DBEntry(pane, "Spawn Dist: ", Float.toString(rows.getFloat("spawndist")), 500, 270);
                cWayPoint = new DBEntry(pane, "Current Way Point: ", Integer.toString(rows.getInt("currentwaypoint")), 500, 330);
                cHp = new DBEntry(pane, "Current Health: ", Integer.toString(rows.getInt("curhealth")), 900, 90);
                cMana = new DBEntry(pane, "Current Mana: ", Integer.toString(rows.getInt("curmana")), 900, 120);
                moveType = new DBEntry(pane, "Movement Type: ", Integer.toString(rows.getInt("MovementType")), 900, 180);
                npcFlag = new DBEntry(pane, "NPC Flags: ", Integer.toString(rows.getInt("npcflag")), 900, 240);
                unitFlags = new DBEntry(pane, "Unit Flags: ", Integer.toString(rows.getInt("unit_flags")), 900, 270);
                dynFlags = new DBEntry(pane, "Dynamic Flags: ", Integer.toString(rows.getInt("dynamicflags")), 900, 300);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        //searchButton
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
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
            public void handle(ActionEvent event)
            {
                String statement = "UPDATE creature SET " +
                        "guid = "+guid.getVal()+"," +
                        "id = "+id.getVal()+"," +
                        "map = "+map.getVal()+"," +
                        "spawnMask = "+spawnMask.getVal()+"," +
                        "phaseMask = "+phaseMask.getVal()+"," +
                        "modelid = "+modelId.getVal()+"," +
                        "equipment_id = "+equipmentId.getVal()+"," +
                        "position_x = "+posX.getVal()+"," +
                        "position_y = "+posY.getVal()+"," +
                        "position_z = "+posZ.getVal()+"," +
                        "orientation = "+orientation.getVal()+"," +
                        "spawntimesecs = "+spawnTime.getVal()+"," +
                        "spawndist = "+spawnDist.getVal()+"," +
                        "currentwaypoint = "+cWayPoint.getVal()+"," +
                        "curhealth = "+cHp.getVal()+"," +
                        "curmana = "+cMana.getVal()+"," +
                        "MovementType = "+moveType.getVal()+"," +
                        "npcflag = "+npcFlag.getVal()+"," +
                        "unit_flags = "+unitFlags.getVal()+"," +
                        "dynamicflags = "+dynFlags.getVal()+" WHERE guid = "+ gId +";";
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
