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

    public CreatureTemplate(StackPane p, String i, DBInitialise c){
        pane = p;
        conn = c;
        id = i;
        construct();
    }

    private void construct(){
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
        String minRangeDmg = "";
        String maxRangeDmg = "";
        String rangeAttackPower = "";
        String dmgSchool = "";
        String attPower = "";
        String dmgMult = "";
        String baseAttackTime = "";
        String rangeAttackTime = "";
        String unitClass = "";
        String[] unitFlags = new String[2];
        String dynamicFlags = "";
        String creatureFamily = "";
        String trainerType = "";
        String trainerClass = "";
        String trainerRace = "";
        String npcType = "";
        String npcTypeFlag[] = new String[2];
        String lootId = "";
        String pickpocketLootId = "";
        String skinLootId = "";
        String resistanceVal[] = new String[6];
        String spell[] = new String[8];
        String petSpellDataId = "";
        String vehicleId = "";
        String minGold = "";
        String maxGold = "";
        String aiName = "";
        String movementType = "";
        String inhabitType = "";
        String hoverHeight = "";
        String healthMod = "";
        String manaMod = "";
        String manaModEx = "";
        String armorMod = "";
        String racialLeader = "";
        String questItem[] = new String[6];
        String movementId = "";
        String regenHealth = "";
        String mechanicImmuneMask = "";
        String flagsExtra = "";
        String scriptName = "";
        String wdbVerified = "";
        String statement = "SELECT * FROM creature_template WHERE entry = " + id;
        ResultSet rows = conn.processQuery(statement);
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
                minLevel = Integer.toString(rows.getInt("minlevel"));
                maxLevel = Integer.toString(rows.getInt("maxlevel"));
                exp = Integer.toString(rows.getInt("exp"));
                expUnk = Integer.toString(rows.getInt("exp_unk"));
                factionA = Integer.toString(rows.getInt("faction_A"));
                factionH = Integer.toString(rows.getInt("faction_H"));
                npcFlag = Integer.toString(rows.getInt("npcflag"));
                walkSpeed = Float.toString(rows.getFloat("speed_walk"));
                runSpeed = Float.toString(rows.getFloat("speed_run"));
                scale = Float.toString(rows.getFloat("scale"));
                rank = Integer.toString(rows.getInt("rank"));
                minDmg = Float.toString(rows.getFloat("mindmg"));
                maxDmg = Float.toString(rows.getFloat("maxdmg"));
                minRangeDmg = Float.toString(rows.getFloat("minrangedmg"));
                maxRangeDmg = Float.toString(rows.getFloat("maxrangedmg"));
                dmgSchool = Integer.toString(rows.getInt("dmgschool"));
                attPower = Integer.toString(rows.getInt("attackpower"));
                dmgMult = Float.toString(rows.getFloat("dmg_multiplier"));
                baseAttackTime = Integer.toString(rows.getInt("baseattacktime"));
                rangeAttackTime = Integer.toString(rows.getInt("rangeattacktime"));
                unitClass = Integer.toString(rows.getInt("unit_class"));
                unitFlags[0] = Integer.toString(rows.getInt("unit_flags"));
                unitFlags[1] = Integer.toString(rows.getInt("unit_flags2"));
                dynamicFlags = Integer.toString(rows.getInt("dynamicflags"));
                creatureFamily = Integer.toString(rows.getInt("family"));
                trainerType = Integer.toString(rows.getInt("trainer_type"));
                trainerClass = Integer.toString(rows.getInt("trainer_class"));
                trainerRace = Integer.toString(rows.getInt("trainer_race"));
                rangeAttackPower = Integer.toString(rows.getInt("rangedattackpower"));
                npcType = Integer.toString(rows.getInt("type"));
                npcTypeFlag[0] = Integer.toString(rows.getInt("type_flags"));
                npcTypeFlag[1] = Integer.toString(rows.getInt("type_flags2"));
                lootId = Integer.toString(rows.getInt("lootid"));
                pickpocketLootId = Integer.toString(rows.getInt("pickpocketloot"));
                skinLootId = Integer.toString(rows.getInt("skinloot"));
                for(int i = 0; i < resistanceVal.length; i++){
                    resistanceVal[i] = Integer.toString(rows.getInt("resistance"+(i+1)));
                }
                for(int i = 0; i < spell.length; i++){
                    spell[i] = Integer.toString(rows.getInt("spell"+(i+1)));
                }
                petSpellDataId = Integer.toString(rows.getInt("PetSpellDataId"));
                vehicleId = Integer.toString(rows.getInt("VehicleId"));
                minGold = Integer.toString(rows.getInt("mingold"));
                maxGold = Integer.toString(rows.getInt("maxgold"));
                aiName = rows.getString("AIName");
                movementType = Integer.toString(rows.getInt("MovementType"));
                inhabitType = Integer.toString(rows.getInt("InhabitType"));
                hoverHeight = Float.toString(rows.getFloat("HoverHeight"));
                healthMod = Float.toString(rows.getFloat("Health_mod"));
                manaMod = Float.toString(rows.getFloat("Mana_mod"));
                manaModEx = Float.toString(rows.getFloat("Mana_mod_extra"));
                armorMod = Float.toString(rows.getFloat("Armor_mod"));
                racialLeader = Integer.toString(rows.getInt("RacialLeader"));
                for(int i = 0; i < questItem.length; i++){
                    questItem[i] = Integer.toString(rows.getInt("questItem"+(i+1)));
                }
                movementId = Integer.toString(rows.getInt("movementId"));
                regenHealth = Integer.toString(rows.getInt("RegenHealth"));
                mechanicImmuneMask = Integer.toString(rows.getInt("mechanic_immune_mask"));
                flagsExtra = Integer.toString(rows.getInt("flags_extra"));
                scriptName = rows.getString("ScriptName");
                wdbVerified = Integer.toString(rows.getInt("WDBVerified"));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        DBEntry entry = new DBEntry(pane, "NPC Entry ID: ", id, 10, 10);
        DBEntry npcName = new DBEntry(pane, "NPC Name: ", name, 10, 90);
        DBEntry sName = new DBEntry(pane, "Sub Name (<> prefix): ", subName, 10, 120);
        DBEntry iName = new DBEntry(pane, "(Cursor) Icon Type: ", iconName, 10, 150);
        DBEntry[] dungDiff = new DBEntry[3];
        for(int i = 0; i < 3; i++){
            dungDiff[i] = new DBEntry(pane, "Dungeon Difficulty Entry " + (i+1)+": ", dungeonDiff[i], 10, 200+(i*30));
        }
        DBEntry[] killCredit = new DBEntry[2];
        for(int i = 0; i < 2; i++){
            killCredit[i] = new DBEntry(pane, "Quest Kill Credit Entry " + (i+1) +": ", killCr[i], 10, 300+(i*30));
        }
        DBEntry[] modelIds = new DBEntry[4];
        for(int i = 0; i < 4; i++){
            modelIds[i] = new DBEntry(pane, "Model id " + (i+1) +": ", modelId[i], 10, 400+(i*30));
        }
        DBEntry gossip = new DBEntry(pane, "Gossip Menu id: ", gossipMenuId, 500, 90);
        DBEntry minLvl = new DBEntry(pane, "Min Level: ", minLevel, 500, 140);
        DBEntry maxLvl = new DBEntry(pane, "Max Level: ", maxLevel, 500, 170);
        DBEntry hpTab = new DBEntry(pane, "HP Table (exp): ", exp, 500, 240);
        DBEntry hpTabUnk = new DBEntry(pane, "HP Table (exp_unk): ", expUnk, 500, 270);
        DBEntry facA = new DBEntry(pane, "Faction Alliance: ", factionA, 500, 330);
        DBEntry facH = new DBEntry(pane, "Faction Horde: ", factionH, 500, 360);
        DBEntry npcF = new DBEntry(pane, "NPC Type Flag: ", npcFlag, 500, 420);
        DBEntry speedW = new DBEntry(pane, "Walk Speed: ", walkSpeed, 900, 360);
        DBEntry speedR = new DBEntry(pane, "Run Speed: ", runSpeed, 900, 390);
        DBEntry npcS = new DBEntry(pane, "NPC Scale Size: ", scale, 500, 480);
        DBEntry npcRank = new DBEntry(pane, "NPC Power Rank: ", rank, 900, 450);
        DBEntry minDamage = new DBEntry(pane, "Min Damage: ", minDmg, 900, 510);
        DBEntry maxDamage = new DBEntry(pane, "Max Damage: ", minDmg, 900, 540);
        DBEntry dmgSchoolType = new DBEntry(pane, "NPC Damage School Type: ", dmgSchool, 10, 540);
        DBEntry attackPower = new DBEntry(pane, "Attack Power: ", attPower, 900, 570);
        DBEntry minRDamage = new DBEntry(pane, "Min Range Damage: ", minRangeDmg, 900, 600);
        DBEntry maxRDamage = new DBEntry(pane, "Max Range Damage: ", maxRangeDmg, 900, 630);
        DBEntry rangeAttckPower = new DBEntry(pane, "Range Attack Power: ", rangeAttackPower, 900, 660);
        DBEntry dmgMultiplier = new DBEntry(pane, "NPC Damage Multiplier: ", dmgMult, 10, 600);
        DBEntry baseAttTime = new DBEntry(pane, "Base Attack Time: ", baseAttackTime, 500, 540);
        DBEntry rangeAttTime = new DBEntry(pane, "Range Attack Time: ", rangeAttackTime, 500, 570);
        DBEntry uClass = new DBEntry(pane, "Unit Class: ", unitClass, 500, 630);
        DBEntry uFlags1 = new DBEntry(pane, "Unit Flags: ", unitFlags[0], 900, 720);
        DBEntry uFlags2 = new DBEntry(pane, "Unit Flags 2: ", unitFlags[1], 900, 750);
        DBEntry dynFlags = new DBEntry(pane, "Dynamic Flags: ", dynamicFlags, 900, 780);
        DBEntry cFamily = new DBEntry(pane, "Creature Family: ", creatureFamily, 10, 660);
        DBEntry trainerT = new DBEntry(pane, "Trainer Type: ", trainerType, 10, 720);
        DBEntry trainerC = new DBEntry(pane, "Trainer Class: ", trainerClass, 10, 750);
        DBEntry trainerR = new DBEntry(pane, "Trainer Race: ", trainerRace, 10, 780);
        DBEntry npcRType = new DBEntry(pane, "NPC Race Type: ", npcType, 500, 690);
        DBEntry npcRTypeFlag = new DBEntry(pane, "NPC Race Type Flags 1: ", npcTypeFlag[0], 500, 720);
        DBEntry npcRTypeFlag2 = new DBEntry(pane, "NPC Race Type Flags 2: ", npcTypeFlag[1], 500, 750);
        DBEntry lootEntry = new DBEntry(pane, "Loot id: ", lootId, 500, 810);
        DBEntry ppEntry = new DBEntry(pane, "Pickpocket loot id: ", pickpocketLootId, 500, 840);
        DBEntry skinEntry = new DBEntry(pane, "Skinning loot id: ", skinLootId, 500, 870);
        DBEntry[] resistanceEntry = new DBEntry[resistanceVal.length];
        for(int i = 0; i < resistanceEntry.length; i++){
            resistanceEntry[i] = new DBEntry(pane, "Resistance "+(i+1)+" Value: ", resistanceVal[i], 10, 840+(i*30));
        }
        DBEntry[] spellId = new DBEntry[spell.length];
        for(int i = 0; i < spellId.length; i++){
            spellId[i] = new DBEntry(pane, "Spell "+(i+1)+" id: ", spell[i], 900, 840+(i*30));
        }
        DBEntry petSpellId = new DBEntry(pane, "Pet Spell Data id: ", petSpellDataId, 500, 930);
        DBEntry vehId = new DBEntry(pane, "Vehicle id: ", vehicleId, 500, 990);
        DBEntry minG = new DBEntry(pane, "Min Gold: ", minGold, 500, 1050);
        DBEntry maxG = new DBEntry(pane, "Max Gold: ", maxGold, 500, 1080);
        DBEntry ai = new DBEntry(pane, "AI Name: ", aiName, 10, 1050);
        DBEntry moveType = new DBEntry(pane, "Movement Type: ", movementType, 10, 1110);
        DBEntry inhabType = new DBEntry(pane, "Inhabit Type: ", inhabitType, 10, 1140);
        DBEntry hovHeight = new DBEntry(pane, "Hover Height: ", hoverHeight, 500, 1140);
        DBEntry hpMod = new DBEntry(pane, "Health mod: ", healthMod, 900, 1110);
        DBEntry manMod = new DBEntry(pane, "Mana mod: ", manaMod, 900, 1140);
        DBEntry manModEx = new DBEntry(pane, "Mana mod extra: ", manaModEx, 900, 1170);
        DBEntry armMod = new DBEntry(pane, "Armor mod: ", armorMod, 900, 1200);
        DBEntry raceLead = new DBEntry(pane, "Racial Leader: ", racialLeader, 10, 1200);
        DBEntry qItem[] = new DBEntry[questItem.length];
        for(int i = 0; i < qItem.length; i++){
            qItem[i] = new DBEntry(pane, "Quest Item "+(i+1)+": ", questItem[i], 500, 1200+(i*30));
        }
        DBEntry moveId = new DBEntry(pane, "Movement id: ", movementId, 10, 1260);
        DBEntry regenHP = new DBEntry(pane, "Regen HP: ", regenHealth, 10, 1320);
        DBEntry mechImmune = new DBEntry(pane, "Mechanic Immune Mask: ", mechanicImmuneMask, 900, 1260);
        DBEntry flagsEx = new DBEntry(pane, "Flags Extra: ", flagsExtra, 900, 1320);
        DBEntry script = new DBEntry(pane, "Script Name: ", scriptName, 10, 1380);
        DBEntry wdbVersion = new DBEntry(pane, "WDB Verified Version: ", wdbVerified, 900, 1380);
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
                        "WDBVerified = \""+wdbVersion.getVal()+"\" WHERE entry = "+ id +"";
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
