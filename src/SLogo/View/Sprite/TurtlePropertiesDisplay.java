package SLogo.View.Sprite;

import SLogo.Turtles.Turtle;
import SLogo.View.ErrorPrompt;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class TurtlePropertiesDisplay {
	Stage myStage;
	Popup myPopup;
	private Integer myID;
	private double[] myPos;
	private Double myHeading;
	private Boolean myHidden;
	private Boolean myPenDown;
	private Turtle myTurtRef;
	
	private TextField newXField;
	private TextField newYField;
	private TextField newHeadingField;
	
	public TurtlePropertiesDisplay(int ID, double[] pos, double heading, boolean hidden, boolean penDown, Turtle turtRef){
		myID = ID;
		myPos = pos;
		myHeading = heading;
		myHidden = hidden;
		myTurtRef = turtRef;
		myPenDown = penDown;
		createStage();
		myStage.setScene(getScene());
	}
	
	public void toggleDisplay(){
	    myStage.show();
	}
	
	private void setProperties(){
		if (matchesDecimalFormat(newXField.getText()) & matchesDecimalFormat(newYField.getText())){
			myTurtRef.setXY(Double.parseDouble(newXField.getText()), Double.parseDouble(newYField.getText()));
		}
		else{
			throw new ErrorPrompt("Position Values not Valid");
		}
		if (matchesDecimalFormat(newHeadingField.getText())){
			myTurtRef.setHeading(Double.parseDouble(newHeadingField.getText()));
		}
		else{
			throw new ErrorPrompt("Heading Value not Valid");
		}
		myTurtRef.setTurtleShow(!myHidden);
		myTurtRef.setPenDown(myPenDown);
		myStage.hide();
	}
	
	private boolean matchesDecimalFormat(String text){
		return text.matches("-?[0-9]+\\.?[0-9]+?");
	}

	private Scene getScene() {
		Pane root = new Pane();
	    
		VBox displayBox = new VBox();
		
		HBox IDBox = new HBox();
	    Label idLabel = new Label(String.format("ID: %s", myID));
	    IDBox.getChildren().add(idLabel);
	    
	    HBox posBox = new HBox();
	    Label posLabel = new Label("Position: ");
	    newXField = new TextField(String.valueOf(myPos[0]));
	    newXField.setPrefWidth(100);
	    newYField = new TextField(String.valueOf(myPos[1]));
	    newYField.setPrefWidth(100);
	    posBox.getChildren().add(posLabel);
	    posBox.getChildren().add(newXField);
	    posBox.getChildren().add(newYField);
	    
	    HBox headingBox = new HBox();
	    Label headingLabel = new Label("Heading: ");
	    newHeadingField = new TextField(String.valueOf(myHeading));
	    newHeadingField.setPrefWidth(100);
	    headingBox.getChildren().add(headingLabel);
	    headingBox.getChildren().add(newHeadingField);
	    
	    HBox hiddenBox = new HBox();
	    Label hiddenLabel = new Label(String.format("Hidden: %B", myHidden));
	    Button hiddenButton = new Button("Toggle Hidden");
	    hiddenButton.setOnAction(e -> toggleHidden(hiddenLabel));
	    hiddenBox.getChildren().add(hiddenLabel);
	    hiddenBox.getChildren().add(hiddenButton);
	    
	    HBox penDownBox = new HBox();
	    Label penDownLabel = new Label(String.format("Pen Down: %B", myHidden));
	    Button penDownButton = new Button("Toggle Pen Down");
	    penDownButton.setOnAction(e -> togglePenDown(penDownLabel));
	    penDownBox.getChildren().add(penDownLabel);
	    penDownBox.getChildren().add(penDownButton);
	    
	    HBox buttonsBox = new HBox();
	    Button closeButton = new Button("Close");
	    closeButton.setOnAction(e -> myStage.close());
	    buttonsBox.getChildren().add(closeButton);
	    Button applyButton = new Button("Apply");
	    applyButton.setOnAction(e -> setProperties());
	    buttonsBox.getChildren().add(applyButton);
	    
	    displayBox.getChildren().add(IDBox);
	    displayBox.getChildren().add(posBox);
	    displayBox.getChildren().add(headingBox);
	    displayBox.getChildren().add(hiddenBox);
	    displayBox.getChildren().add(penDownBox);
	    displayBox.getChildren().add(buttonsBox);
	    root.getChildren().add(displayBox);
	    Scene myPopup= new Scene(root, 300, 200);
	    return myPopup;
	}

	private void togglePenDown(Label penDownLabel) {
		myPenDown = !myPenDown;
		penDownLabel.setText(String.format("Pen Down: %B", myPenDown));
	}

	private void toggleHidden(Label hiddenLabel) {
		myHidden = !myHidden;
		hiddenLabel.setText(String.format("Hidden: %B", myHidden));
	}

	private void createStage() {
		myStage = new Stage();
		myStage.setTitle(String.format("Turtle %d Properties", myID));
	}

	
}
