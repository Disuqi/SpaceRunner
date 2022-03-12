package disuqi.app;

import disuqi.view.View;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    View view;

    @Override
    public void start(Stage stage) {
        view = new View();
        stage = view.getMainStage();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}