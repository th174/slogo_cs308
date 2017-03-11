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
        int width = 1300;
        int height = 700;
        SLogoGUIImpl gui = new SLogoGUIImpl(primaryStage,width, height);
        BorderPane GUIPane = new BorderPane();
        GUIPane.setCenter(gui.getView());
        Scene scene = new Scene(GUIPane, width, height);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        ReplImpl repl = new ReplImpl();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("SLogo >> ");
            repl.read(input.nextLine());
        }
    }
}
