import javafx.application.Application;
import javafx.stage.Stage;

public class Launchpad extends Application {
    UserInterface ui;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui = new UserInterface(primaryStage);
    }

    @Override
    public void stop(){
        try {
            ui.conn.close();
        } catch (Exception ex) {
            System.out.println("No database connection needed closing.");
        }
    }
}
