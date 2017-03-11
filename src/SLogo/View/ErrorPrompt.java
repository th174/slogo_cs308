package SLogo.View;


@SuppressWarnings("serial")
public class ErrorPrompt extends RuntimeException {
	public ErrorPrompt (String message) {
        super(message);
    }
}
