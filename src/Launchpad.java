import javafx.application.Application;
import javafx.stage.Stage;

public class Launchpad extends Application {
    UserInterface ui;

    public static void main(String args[]) {
        launch(args);
        Launchpad main = new Launchpad();
    }

    public Launchpad() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui = new UserInterface(primaryStage);
    }

    @Override
    public void stop(){
        try {
            ui.closeConnection();
        } catch (Exception ex) {
            System.out.println("No database connection needed closing.");
        }
    }
}
