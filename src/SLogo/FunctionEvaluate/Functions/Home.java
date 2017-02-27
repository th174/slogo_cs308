package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;
import SLogo.View.Sprite.Sprite;

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
		Sprite sprite = this.getEnvironment().getSprite();
		Turtle turt = this.getEnvironment().getTurtle();
		return new NumberVariable(move(sprite, turt));
	}
	
	private double move(Sprite sprite, Turtle turt){
		double xPos = sprite.getPosition()[0];
		double yPos = sprite.getPosition()[1];
		
		turt.reset(xPos, yPos);
		
		return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
	}

}
