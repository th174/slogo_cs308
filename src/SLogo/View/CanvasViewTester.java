//package SLogo.View;
//
//import java.io.File;
//
//import javafx.application.Application;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//public class CanvasViewTester extends Application{
//	private CanvasView CV;
//	private Scene scene;
//
//	public void start (Stage primaryStage) throws InvalidImageFileException{
//		CV = new CanvasViewImpl(600, 600);
//		scene = new Scene((Parent) CV.getView(), 600, 600, Color.WHITE);
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        CV.setImage(new File("data/Images/SampleTurtle.png"));
//	}
//	public static void main(String[] args){
//		launch(args);
//	}
//
//}
