package SLogo.View;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorPrompt {
	public ErrorPrompt (String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Turtle Parameter Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
