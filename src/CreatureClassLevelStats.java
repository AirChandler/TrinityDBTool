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

public class CreatureClassLevelStats {

    StackPane pane;
    DBInitialise conn;
    String level;
    String classType;
    //Text Entries
    DBEntry lvl;
    DBEntry classT;
    DBEntry oldContentBaseHp;
    DBEntry currentContentBaseHp;
    DBEntry baseMana;
    DBEntry baseArmor;


    public CreatureClassLevelStats(StackPane p, String l, String cT, DBInitialise c) {
        pane = p;
        conn = c;
        level = l;
        classType = cT;
        construct();
    }

    private void construct() {
        //Text Entries
        lvl = new DBEntry(pane, "NPC Level: ", level, 10, 10);
        classT = new DBEntry(pane, "NPC Class: ", classType, 10, 40);
        String statement = "SELECT * FROM creature_classlevelstats WHERE level = "+level+" AND class = "+classType+";";
        ResultSet rows = conn.processQuery(statement);
        try {
            while (rows.next()) {
                oldContentBaseHp = new DBEntry(pane, "NPC Old Content Base Hp: ", Integer.toString(rows.getInt("OldContentBaseHP")), 10, 90);
                currentContentBaseHp = new DBEntry(pane, "NPC Current Content Base Hp: ", Integer.toString(rows.getInt("CurrentContentBaseHP")), 10, 120);
                baseMana = new DBEntry(pane, "NPC Base Mana: ", Integer.toString(rows.getInt("basemana")), 500, 90);
                baseArmor = new DBEntry(pane, "NPC Base Armor: ", Integer.toString(rows.getInt("basearmor")), 500, 120);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //searchButton
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level = lvl.getVal();
                classType = classT.getVal();
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
                String statement = "UPDATE creature_classlevelstats SET " +
                        "level = " + lvl.getVal() + "," +
                        "class = " + classT.getVal() + "," +
                        "OldContentBaseHP = " + oldContentBaseHp.getVal() + "," +
                        "CurrentContentBaseHP = " + currentContentBaseHp.getVal() + "," +
                        "basemana = " + baseMana.getVal() + "," +
                        "basearmor = " + baseArmor.getVal() + " WHERE level = " + level + " AND class = " + classType + ";";
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
