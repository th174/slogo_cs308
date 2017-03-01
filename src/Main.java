import SLogo.ReplImpl;
import SLogo.View.SLogoGUIImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        
        ReplImpl repl = new ReplImpl(System.in);
        SLogoGUIImpl gui = new SLogoGUIImpl(repl);
        BorderPane GUIPane = new BorderPane();
        GUIPane.setCenter(gui.getView());
        System.out.println("Ayylmao");
        Scene scene = new Scene(GUIPane, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
        ReplImpl repl = new ReplImpl(System.in);
        Scanner input = new Scanner(System.in);
        while (true) {
            repl.read(input);
        }
    }
}
