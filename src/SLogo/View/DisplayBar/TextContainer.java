package SLogo.View.DisplayBar;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class TextContainer implements VisualCommand{
	private TextArea myTextArea;

	public TextContainer(String string) {
		myTextArea = new TextArea(string);
	}
	
	@Override
	public boolean equals(Object object){
		if(!(object instanceof TextContainer)){
			return false;
		}
		TextContainer text = (TextContainer) object;
		return this.myTextArea.getText().equals(text);
	}

	@Override
	public Node getView() {
		return myTextArea;
	}
	
	@Override
	public String getCommand() {
		return myTextArea.getText();
	}
}
