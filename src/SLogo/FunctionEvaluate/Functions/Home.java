package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

/**
 * Implements HOME command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class Home extends TurtleSettings {

	public Home(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		CanvasView canvas = this.getEnvironment().getCanvas();
		Turtle turt = this.getEnvironment().getTurtle();
		return new NumberVariable(move(canvas, turt));
	}
	
	private double move(CanvasView canvas, Turtle turt){
		double xPos = canvas.getSpritePosition()[0];
		double yPos = canvas.getSpritePosition()[1];
		
		turt.reset(xPos, yPos);
		
		return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
	}

}
