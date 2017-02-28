package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

/**
 * Implements CLEARSCREEN/CS command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class ClearScreen extends TurtleSettings {

	public ClearScreen(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		Turtle turt = this.getEnvironment().getTurtle();
		CanvasView canvas = this.getEnvironment().getCanvas();
		return new NumberVariable(move(turt, canvas));
	}
	
	private double move(Turtle turt, CanvasView canvas){
		double xPos = canvas.getSpritePosition()[0];
		double yPos = canvas.getSpritePosition()[1];
		
		canvas.clearScreen();
		
		return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
	}

}