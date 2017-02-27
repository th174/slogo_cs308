package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;
import SLogo.View.Sprite.Sprite;

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
		Sprite sprite = this.getEnvironment().getSprite();
		return new NumberVariable(move(turt, sprite));
	}
	
	private double move(Turtle turt, Sprite sprite){
		double xPos = sprite.getPosition()[0];
		double yPos = sprite.getPosition()[1];
		
		turt.reset(xPos, yPos);
		//TODO Somehow inform front end that screen needs to be cleared
		
		return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
	}

}