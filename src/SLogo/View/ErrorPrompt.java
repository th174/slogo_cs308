package SLogo.View;


public class ErrorPrompt extends RuntimeException {
	public ErrorPrompt (String message) {
        super(message);
    }
}
