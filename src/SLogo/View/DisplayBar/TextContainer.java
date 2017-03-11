package SLogo.View.DisplayBar;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class TextContainer implements VisualCommand{
	private Label myLabel;

	public TextContainer(String string) {
		myLabel = new Label(string);
	}
	
	
	@Override
	public boolean equals(Object object){
		if(!(object instanceof TextContainer)){
			return false;
		}
		TextContainer text = (TextContainer) object;
		return this.myLabel.getText().equals(text);
	}

	@Override
	public Node getView() {
		return myLabel;
	}
	
	@Override
	public String getCommand() {
		return myLabel.getText();
	}
}
