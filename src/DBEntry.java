import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

public class DBEntry {
    TextField text;
    public DBEntry(StackPane root, String label, String entry, int x, int y){
        Label npcName = new Label(label);
        text = new TextField(entry);
        npcName.setPrefSize(npcName.getText().length()*9, 30);
        npcName.setId("label");
        text.setTranslateY(y);
        npcName.setTranslateY(y);
        text.setTranslateX(x+npcName.getPrefWidth());
        npcName.setTranslateX(x);
        text.setMaxWidth(150);
        root.getChildren().addAll(text, npcName);
    }

    public String getVal() {
        return text.getCharacters().toString();
    }
}
