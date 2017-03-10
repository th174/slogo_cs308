package SLogo.FileHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Stone Mathers
 *
 */
public class ConfigurationWriter implements FileWriter {

	private static final int INDENTS = 0;
	private static final String COMMAND_BUNDLE = "resources/files/writerCommands";
	public ResourceBundle myCommands = ResourceBundle.getBundle(COMMAND_BUNDLE);
	private static final String ERROR_BUNDLE = "resources/View/Exceptions";
	public ResourceBundle myErrors = ResourceBundle.getBundle(ERROR_BUNDLE);
	private Property myProperty;
	private String myTag;
	
	public ConfigurationWriter(Property prop) {
		myProperty = prop;
		myTag = myCommands.getString("configWriterTag");
	}

	
	@Override
	public void write(File file) {
		Path filePath = Paths.get(file.toURI());
		ArrayList<String> xmlData = new ArrayList<String>();
		xmlData.add(generateXML());
		try {
			Files.write(filePath, xmlData);
		} catch (IOException e) {
			throw new RuntimeException(String.format(myErrors.getString("FileWritingError"), file.getName()), e);
		}

	}
	
	/**
	 * Creates an XML-formatted String for a Configuration File.
	 * 
	 * @return
	 */
	private String generateXML(){
		String data = myProperty.getXML(INDENTS+ + 1);
		return String.format(myCommands.getString("tag"), INDENTS, myTag, data, INDENTS, myTag);
	}

}
