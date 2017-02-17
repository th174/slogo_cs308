import SLogo.ReplImpl;
import SLogo.SLogoView.SLogoViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
//        launch(args);
        ReplImpl repl = new ReplImpl(System.in, new SLogoViewImpl());
        repl.loop();
    }
}
