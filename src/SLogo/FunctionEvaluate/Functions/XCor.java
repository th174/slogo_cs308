package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.View.CanvasView;

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
		CanvasView canvas = this.getEnvironment().getCanvas();
		return new NumberVariable(canvas.getSpritePosition()[0]);
	}

}
