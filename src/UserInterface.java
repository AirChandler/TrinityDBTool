import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInterface {
    Stage window;
    Scene windowScene;
    DBInitialise conn;
    final int width = 1920;
    final int height = 1080;

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