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
        window.setWidth(1280);
        window.setHeight(720);
        constructMainInterface();
    }

    public void constructMainInterface(){
        MainInterface mainUI = new MainInterface(this);
    }

    public void constructDBInterface(){
        DBInterface dbUI = new DBInterface(this);
    }

    public void closeConnection(){
        conn.close();
    }

}