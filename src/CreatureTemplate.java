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

public class CreatureTemplate {

    StackPane pane;
    DBInitialise conn;
    String id;
    //Text Entries
    DBEntry entry;
    DBEntry npcName;
    DBEntry sName;
    DBEntry iName;
    DBEntry[] dungDiff = new DBEntry[3];
    DBEntry[] killCredit = new DBEntry[2];
    DBEntry[] modelIds = new DBEntry[4];
    DBEntry gossip;
    DBEntry minLvl;
    DBEntry maxLvl;
    DBEntry hpTab;
    DBEntry hpTabUnk;
    DBEntry facA;
    DBEntry facH;
    DBEntry npcF;
    DBEntry speedW;
    DBEntry speedR;
    DBEntry npcS;
    DBEntry npcRank;
    DBEntry minDamage;
    DBEntry maxDamage;
    DBEntry dmgSchoolType;
    DBEntry attackPower;
    DBEntry minRDamage;
    DBEntry maxRDamage;
    DBEntry rangeAttckPower;
    DBEntry dmgMultiplier;
    DBEntry baseAttTime;
    DBEntry rangeAttTime;
    DBEntry uClass;
    DBEntry uFlags1;
    DBEntry uFlags2;
    DBEntry dynFlags;
    DBEntry cFamily;
    DBEntry trainerT;
    DBEntry trainerC;
    DBEntry trainerR;
    DBEntry npcRType;
    DBEntry npcRTypeFlag;
    DBEntry npcRTypeFlag2;
    DBEntry lootEntry;
    DBEntry ppEntry;
    DBEntry skinEntry;
    DBEntry[] resistanceEntry = new DBEntry[6];
    DBEntry[] spellId = new DBEntry[8];
    DBEntry petSpellId;
    DBEntry vehId;
    DBEntry minG;
    DBEntry maxG;
    DBEntry ai;
    DBEntry moveType;
    DBEntry inhabType;
    DBEntry hovHeight;
    DBEntry hpMod;
    DBEntry manMod;
    DBEntry manModEx;
    DBEntry armMod;
    DBEntry raceLead;
    DBEntry qItem[] = new DBEntry[6];
    DBEntry moveId;
    DBEntry regenHP;
    DBEntry mechImmune;
    DBEntry flagsEx;
    DBEntry script;
    DBEntry wdbVersion;

    public CreatureTemplate(StackPane p, String entry, DBInitialise c){
        pane = p;
        conn = c;
        id = entry;
        construct();
    }

    private void construct(){
        //Text Entries
        entry = new DBEntry(pane, "NPC Entry ID: ", id, 10, 10);
        String statement = "SELECT * FROM creature_template WHERE entry = " + id;
        ResultSet rows = conn.processQuery(statement);
        try {
            while (rows.next()) {
                npcName = new DBEntry(pane, "NPC Name: ", rows.getString("name"), 10, 90);
                for(int i = 0; i < dungDiff.length; i++){
                    dungDiff[i] = new DBEntry(pane, "Dungeon Difficulty Entry " + (i+1)+": ",
                            Integer.toString(rows.getInt("Difficulty_Entry_" + (i+1))), 10, 200+(i*30));
                }
                for(int i = 0; i < killCredit.length; i++){
                    killCredit[i] = new DBEntry(pane, "Quest Kill Credit Entry " + (i+1) +": ",
                            Integer.toString(rows.getInt("KillCredit"+(i+1))), 10, 300+(i*30));
                }
                for(int i = 0; i < modelIds.length; i++){
                    modelIds[i] = new DBEntry(pane, "Model id " + (i+1) +": ",
                            Integer.toString(rows.getInt("modelid"+(i+1))), 10, 400+(i*30));
                }
                sName = new DBEntry(pane, "Sub Name (<> prefix): ", rows.getString("subname"), 10, 120);
                iName = new DBEntry(pane, "(Cursor) Icon Type: ", rows.getString("IconName"), 10, 150);
                gossip = new DBEntry(pane, "Gossip Menu id: ", Integer.toString(rows.getInt("gossip_menu_id")), 500, 90);
                minLvl = new DBEntry(pane, "Min Level: ", Integer.toString(rows.getInt("minlevel")), 500, 140);
                maxLvl = new DBEntry(pane, "Max Level: ", Integer.toString(rows.getInt("maxlevel")), 500, 170);
                hpTab = new DBEntry(pane, "HP Table (exp): ", Integer.toString(rows.getInt("exp")), 500, 240);
                hpTabUnk = new DBEntry(pane, "HP Table (exp_unk): ", Integer.toString(rows.getInt("exp_unk")), 500, 270);
                facA = new DBEntry(pane, "Faction Alliance: ", Integer.toString(rows.getInt("faction_A")), 500, 330);
                facH = new DBEntry(pane, "Faction Horde: ", Integer.toString(rows.getInt("faction_H")), 500, 360);
                npcF = new DBEntry(pane, "NPC Type Flag: ", Integer.toString(rows.getInt("npcflag")), 500, 420);
                speedW = new DBEntry(pane, "Walk Speed: ", Float.toString(rows.getFloat("speed_walk")), 900, 360);
                speedR = new DBEntry(pane, "Run Speed: ", Float.toString(rows.getFloat("speed_run")), 900, 390);
                npcS = new DBEntry(pane, "NPC Scale Size: ", Float.toString(rows.getFloat("scale")), 500, 480);
                npcRank = new DBEntry(pane, "NPC Power Rank: ", Integer.toString(rows.getInt("rank")), 900, 450);
                minDamage = new DBEntry(pane, "Min Damage: ", Float.toString(rows.getFloat("mindmg")), 900, 510);
                maxDamage = new DBEntry(pane, "Max Damage: ", Float.toString(rows.getFloat("maxdmg")), 900, 540);
                dmgSchoolType = new DBEntry(pane, "NPC Damage School Type: ", Integer.toString(rows.getInt("dmgschool")), 10, 540);
                attackPower = new DBEntry(pane, "Attack Power: ", Integer.toString(rows.getInt("attackpower")), 900, 570);
                minRDamage = new DBEntry(pane, "Min Range Damage: ", Float.toString(rows.getFloat("minrangedmg")), 900, 600);
                maxRDamage = new DBEntry(pane, "Max Range Damage: ", Float.toString(rows.getFloat("maxrangedmg")), 900, 630);
                rangeAttckPower = new DBEntry(pane, "Range Attack Power: ", Integer.toString(rows.getInt("rangedattackpower")), 900, 660);
                dmgMultiplier = new DBEntry(pane, "NPC Damage Multiplier: ", Float.toString(rows.getFloat("dmg_multiplier")), 10, 600);
                baseAttTime = new DBEntry(pane, "Base Attack Time: ", Integer.toString(rows.getInt("baseattacktime")), 500, 540);
                rangeAttTime = new DBEntry(pane, "Range Attack Time: ", Integer.toString(rows.getInt("rangeattacktime")), 500, 570);
                uClass = new DBEntry(pane, "Unit Class: ", Integer.toString(rows.getInt("unit_class")), 500, 630);
                uFlags1 = new DBEntry(pane, "Unit Flags: ", Integer.toString(rows.getInt("unit_flags")), 900, 720);
                uFlags2 = new DBEntry(pane, "Unit Flags 2: ", Integer.toString(rows.getInt("unit_flags2")), 900, 750);
                dynFlags = new DBEntry(pane, "Dynamic Flags: ", Integer.toString(rows.getInt("dynamicflags")), 900, 780);
                cFamily = new DBEntry(pane, "Creature Family: ", Integer.toString(rows.getInt("family")), 10, 660);
                trainerT = new DBEntry(pane, "Trainer Type: ", Integer.toString(rows.getInt("trainer_type")), 10, 720);
                trainerC = new DBEntry(pane, "Trainer Class: ", Integer.toString(rows.getInt("trainer_class")), 10, 750);
                trainerR = new DBEntry(pane, "Trainer Race: ", Integer.toString(rows.getInt("trainer_race")), 10, 780);
                npcRType = new DBEntry(pane, "NPC Race Type: ", Integer.toString(rows.getInt("type")), 500, 690);
                npcRTypeFlag = new DBEntry(pane, "NPC Race Type Flags 1: ", Integer.toString(rows.getInt("type_flags")), 500, 720);
                npcRTypeFlag2 = new DBEntry(pane, "NPC Race Type Flags 2: ", Integer.toString(rows.getInt("type_flags2")), 500, 750);
                lootEntry = new DBEntry(pane, "Loot id: ", Integer.toString(rows.getInt("lootid")), 500, 810);
                ppEntry = new DBEntry(pane, "Pickpocket loot id: ", Integer.toString(rows.getInt("pickpocketloot")), 500, 840);
                skinEntry = new DBEntry(pane, "Skinning loot id: ", Integer.toString(rows.getInt("skinloot")), 500, 870);
                for(int i = 0; i < resistanceEntry.length; i++){
                    resistanceEntry[i] = new DBEntry(pane, "Resistance "+(i+1)+" Value: ", Integer.toString(rows.getInt("resistance"+(i+1))), 10, 840+(i*30));
                }
                for(int i = 0; i < spellId.length; i++){
                    spellId[i] = new DBEntry(pane, "Spell "+(i+1)+" id: ", Integer.toString(rows.getInt("spell"+(i+1))), 900, 840+(i*30));
                }
                petSpellId = new DBEntry(pane, "Pet Spell Data id: ", Integer.toString(rows.getInt("PetSpellDataId")), 500, 930);
                vehId = new DBEntry(pane, "Vehicle id: ", Integer.toString(rows.getInt("VehicleId")), 500, 990);
                minG = new DBEntry(pane, "Min Gold: ", Integer.toString(rows.getInt("mingold")), 500, 1050);
                maxG = new DBEntry(pane, "Max Gold: ", Integer.toString(rows.getInt("maxgold")), 500, 1080);
                ai = new DBEntry(pane, "AI Name: ", rows.getString("AIName"), 10, 1050);
                moveType = new DBEntry(pane, "Movement Type: ", Integer.toString(rows.getInt("MovementType")), 10, 1110);
                inhabType = new DBEntry(pane, "Inhabit Type: ", Integer.toString(rows.getInt("InhabitType")), 10, 1140);
                hovHeight = new DBEntry(pane, "Hover Height: ", Float.toString(rows.getFloat("HoverHeight")), 500, 1140);
                hpMod = new DBEntry(pane, "Health mod: ", Float.toString(rows.getFloat("Health_mod")), 900, 1110);
                manMod = new DBEntry(pane, "Mana mod: ", Float.toString(rows.getFloat("Mana_mod")), 900, 1140);
                manModEx = new DBEntry(pane, "Mana mod extra: ", Float.toString(rows.getFloat("Mana_mod_extra")), 900, 1170);
                armMod = new DBEntry(pane, "Armor mod: ", Float.toString(rows.getFloat("Armor_mod")), 900, 1200);
                raceLead = new DBEntry(pane, "Racial Leader: ", Integer.toString(rows.getInt("RacialLeader")), 10, 1200);
                for(int i = 0; i < qItem.length; i++){
                    qItem[i] = new DBEntry(pane, "Quest Item "+(i+1)+": ", Integer.toString(rows.getInt("questItem"+(i+1))), 500, 1200+(i*30));
                }
                moveId = new DBEntry(pane, "Movement id: ", Integer.toString(rows.getInt("movementId")), 10, 1260);
                regenHP = new DBEntry(pane, "Regen HP: ", Integer.toString(rows.getInt("RegenHealth")), 10, 1320);
                mechImmune = new DBEntry(pane, "Mechanic Immune Mask: ", Integer.toString(rows.getInt("mechanic_immune_mask")), 900, 1260);
                flagsEx = new DBEntry(pane, "Flags Extra: ", Integer.toString(rows.getInt("flags_extra")), 900, 1320);
                script = new DBEntry(pane, "Script Name: ", rows.getString("ScriptName"), 10, 1380);
                wdbVersion = new DBEntry(pane, "WDB Verified Version: ", Integer.toString(rows.getInt("WDBVerified")), 900, 1380);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        //NPC Model
        Rectangle npcModel = new Rectangle();
        npcModel.setWidth(300);
        npcModel.setHeight(300);
        try {
            String subDir = Character.toString(modelIds[0].getVal().charAt(0));
            String imgUrl = "http://mop-static.tauri.hu/npc/" + subDir + "/creature-display-" + modelIds[0].getVal() + ".jpg";
            Image backImg = new Image(imgUrl);
            ImagePattern pattern = new ImagePattern(backImg);
            npcModel.setFill(pattern);
        } catch (Exception ex){
            npcModel.setVisible(false);
        }
        npcModel.setTranslateX(1200-npcModel.getWidth());
        npcModel.setTranslateY(10);
        //Setting the height and width of the arc
        npcModel.setArcWidth(50.0);
        npcModel.setArcHeight(50.0);
        npcModel.setStroke(rgb(112, 63, 1));
        npcModel.setStrokeWidth(5);
        pane.getChildren().add(npcModel);
        //searchButton
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
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
            public void handle(ActionEvent event)
            {
                String statement = "UPDATE creature_template SET " +
                        "entry = "+entry.getVal()+"," +
                        "difficulty_entry_1 = "+dungDiff[0].getVal()+"," +
                        "difficulty_entry_2 = "+dungDiff[1].getVal()+"," +
                        "difficulty_entry_3 = "+dungDiff[2].getVal()+"," +
                        "KillCredit1 = "+killCredit[0].getVal()+"," +
                        "KillCredit2 = "+killCredit[1].getVal()+"," +
                        "modelid1 = "+modelIds[0].getVal()+"," +
                        "modelid2 = "+modelIds[1].getVal()+"," +
                        "modelid3 = "+modelIds[2].getVal()+"," +
                        "modelid4 = "+modelIds[3].getVal()+"," +
                        "name = \""+npcName.getVal()+"\"," +
                        "subname = \""+sName.getVal()+"\"," +
                        "IconName = \""+iName.getVal()+"\"," +
                        "gossip_menu_id = "+gossip.getVal()+"," +
                        "minlevel = "+minLvl.getVal()+"," +
                        "maxlevel = "+maxLvl.getVal()+"," +
                        "exp = "+hpTab.getVal()+"," +
                        "exp_unk = "+hpTabUnk.getVal()+"," +
                        "faction_A = "+facA.getVal()+"," +
                        "faction_H = "+facH.getVal()+"," +
                        "npcflag = "+npcF.getVal()+"," +
                        "speed_walk = "+speedW.getVal()+"," +
                        "speed_run = "+speedR.getVal()+"," +
                        "scale = "+npcS.getVal()+"," +
                        "rank = "+npcRank.getVal()+"," +
                        "mindmg = "+minDamage.getVal()+"," +
                        "maxdmg = "+maxDamage.getVal()+"," +
                        "dmgschool = "+dmgSchoolType.getVal()+"," +
                        "attackpower = "+attackPower.getVal()+"," +
                        "dmg_multiplier = "+dmgMultiplier.getVal()+"," +
                        "baseattacktime = "+baseAttTime.getVal()+"," +
                        "rangeattacktime = "+rangeAttTime.getVal()+"," +
                        "unit_class = "+uClass.getVal()+"," +
                        "unit_flags = "+uFlags1.getVal()+"," +
                        "unit_flags2 = "+uFlags2.getVal()+"," +
                        "dynamicflags = "+dynFlags.getVal()+"," +
                        "family = "+cFamily.getVal()+"," +
                        "trainer_type = "+trainerT.getVal()+"," +
                        "trainer_class = "+trainerC.getVal()+"," +
                        "trainer_race = "+trainerR.getVal()+"," +
                        "minrangedmg = "+minRDamage.getVal()+"," +
                        "maxrangedmg = "+maxRDamage.getVal()+"," +
                        "rangedattackpower = "+rangeAttckPower.getVal()+"," +
                        "type = "+npcRType.getVal()+"," +
                        "type_flags = "+npcRTypeFlag.getVal()+"," +
                        "type_flags2 = "+npcRTypeFlag2.getVal()+"," +
                        "lootid = "+lootEntry.getVal()+"," +
                        "pickpocketloot = "+ppEntry.getVal()+"," +
                        "skinloot = "+skinEntry.getVal()+"," +
                        "resistance1 = "+resistanceEntry[0].getVal()+"," +
                        "resistance2 = "+resistanceEntry[1].getVal()+"," +
                        "resistance3 = "+resistanceEntry[2].getVal()+"," +
                        "resistance4 = "+resistanceEntry[3].getVal()+"," +
                        "resistance5 = "+resistanceEntry[4].getVal()+"," +
                        "resistance6 = "+resistanceEntry[5].getVal()+"," +
                        "spell1 = "+spellId[0].getVal()+"," +
                        "spell2 = "+spellId[1].getVal()+"," +
                        "spell3 = "+spellId[2].getVal()+"," +
                        "spell4 = "+spellId[3].getVal()+"," +
                        "spell5 = "+spellId[4].getVal()+"," +
                        "spell6 = "+spellId[5].getVal()+"," +
                        "spell7 = "+spellId[6].getVal()+"," +
                        "spell8 = "+spellId[7].getVal()+"," +
                        "PetSpellDataId = "+petSpellId.getVal()+"," +
                        "VehicleId = "+vehId.getVal()+"," +
                        "mingold = "+minG.getVal()+"," +
                        "maxgold = "+maxG.getVal()+"," +
                        "AIName = \""+ai.getVal()+"\"," +
                        "MovementType = "+moveType.getVal()+"," +
                        "InhabitType = "+inhabType.getVal()+"," +
                        "HoverHeight = "+hovHeight.getVal()+"," +
                        "Health_mod = "+hpMod.getVal()+"," +
                        "Mana_mod = "+manMod.getVal()+"," +
                        "Mana_mod_extra = "+manModEx.getVal()+"," +
                        "Armor_mod = "+armMod.getVal()+"," +
                        "RacialLeader = "+raceLead.getVal()+"," +
                        "questItem1 = "+qItem[0].getVal()+"," +
                        "questItem2 = "+qItem[1].getVal()+"," +
                        "questItem3 = "+qItem[2].getVal()+"," +
                        "questItem4 = "+qItem[3].getVal()+"," +
                        "questItem5 = "+qItem[4].getVal()+"," +
                        "questItem6 = "+qItem[5].getVal()+"," +
                        "movementId = "+moveId.getVal()+"," +
                        "RegenHealth = "+regenHP.getVal()+"," +
                        "mechanic_immune_mask = "+mechImmune.getVal()+"," +
                        "flags_extra = "+flagsEx.getVal()+"," +
                        "ScriptName = \""+script.getVal()+"\"," +
                        "WDBVerified = \""+wdbVersion.getVal()+"\" WHERE entry = "+ id +";";
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
