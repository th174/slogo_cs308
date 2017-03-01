package SLogo.View;

import SLogo.View.SLogoGUIImpl;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUITest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	SLogoGUI guitest = new SLogoGUIImpl(null);
        Node root = guitest.getView();
        BorderPane GUIPane = new BorderPane();
        GUIPane.setCenter(root);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(GUIPane, 1000, 1000));
        //primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}