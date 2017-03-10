package SLogo.View.Sprite;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

public class PropertiesDisplay {
	
	public PropertiesDisplay(int ID, double[] pos, double heading, boolean hidden, boolean penDown){
		//Label moreInfo = new Label("ID: " + ID + "\nLocation: " + pos[0] + "-" + pos[1] + "\nHeading: " + heading + 
		//		"\nHidden: " + hidden + "\nPenDown: " + penDown);
		createPopup();
	}
	
	public void toggleDisplay(MouseEvent e){
		
	}
	
	private Popup createPopup() {
	    final Popup popup = new Popup();
	    popup.setAutoHide(true);
	    popup.setX(300);
	    popup.setY(200);
	    popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
	    return popup;
	}

}
