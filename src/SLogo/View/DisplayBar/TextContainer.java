package SLogo.View.DisplayBar;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Container to be able to add equals command
 *
 * @author Alex
 */
public class TextContainer implements VisualCommand {
    private Label myLabel;

    /**
     * New textContainer
     *
     * @param string
     */
    public TextContainer(String string) {
        myLabel = new Label(string);
    }

    /**
     * Equals extended
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TextContainer)) {
            return false;
        }
        TextContainer text = (TextContainer) object;
        return this.myLabel.getText().equals(text);
    }

    /**
     * Gets node
     */
    @Override
    public Node getView() {
        return myLabel;
    }

    /**
     * Gets command representation
     */
    @Override
    public String getCommand() {
        return myLabel.getText();
    }
}
