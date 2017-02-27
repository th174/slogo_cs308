package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;
import SLogo.View.Sprite.Sprite;

/**
 * Implements XCOR command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class XCor extends TurtleSettings {

	public XCor(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		Sprite sprite = this.getEnvironment().getSprite();
		return new NumberVariable(sprite.getPosition()[0]);
	}

}
