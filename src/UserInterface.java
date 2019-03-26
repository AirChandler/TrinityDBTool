import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserInterface {
    Stage window;
    Scene windowScene;
    DBInitialise conn;
    final int width = 1280;
    final int height = 720;

    public UserInterface(Stage w){
        window = w;
        window.setWidth(width);
        window.setHeight(height);
        constructMainInterface();
    }

    public void constructMainInterface(){
        MainInterface mainUI = new MainInterface(this);
    }

    public void constructDBInterface(){
        DBInterface dbUI = new DBInterface(this);
    }

}