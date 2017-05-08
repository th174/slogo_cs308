package SLogo.FunctionEvaluate.Functions.DefinedFunctions;

import SLogo.FunctionEvaluate.Functions.BinaryIterable;
import SLogo.FunctionEvaluate.PredefinedCommandList;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

/**
 * @author Created by th174 on 5/8/2017.
 */
public class WriteAppend implements BinaryIterable {
	@Override
	public Variable operation(Variable var1, Variable var2) throws Exception {
		Files.write(Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + var1.stringContext()), Collections.singletonList(var2.stringContext()), Charset.defaultCharset(), StandardOpenOption.APPEND);
		return PredefinedCommandList.SUM.accumulate(var1, var2);
	}
}
