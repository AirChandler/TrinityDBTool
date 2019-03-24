import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

public class DBEntry {
    TextField entry;
    public DBEntry(StackPane[] root, String label, String Entry, int x, int y){
        Label npcName = new Label(label);
        entry = new TextField(Entry);
        npcName.setPrefSize(100, 30);
        npcName.setId("label");
        entry.setTranslateY(y);
        npcName.setTranslateY(y);
        entry.setTranslateX(x+10);
        npcName.setTranslateX(entry.getTranslateX()-npcName.getPrefWidth());
        entry.setMaxWidth(entry.getText().length()*10);
        root[0].getChildren().addAll(entry, npcName);
    }
}
