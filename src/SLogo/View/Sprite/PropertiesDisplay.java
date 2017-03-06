package SLogo.View.Sprite;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PropertiesDisplay {
	
	public PropertiesDisplay(int ID, int[] pos, int heading, boolean hidden, boolean penDown){
		Label moreInfo = new Label("ID: " + ID + "\nLocation: " + pos[0] + "-" + pos[1] + "\nHeading: " + heading + 
				"\nHidden: " + hidden + "\nPenDown: " + penDown);
	}
	
	public void toggleDisplay(MouseEvent e){
		
	}

}
