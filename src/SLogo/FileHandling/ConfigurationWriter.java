package SLogo.FileHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * ConfigurationWriter takes in a Property at instantiation. It can then
 * write all data in XML format into a selected file.
 * 
 * @author Stone Mathers
 * Created 3/10/2017
 */
public class ConfigurationWriter implements FileWriter {

	private static final String NO_INDENT = "";
	private static final int INDENTS = 0;
	private static final String COMMAND_BUNDLE = "resources/files/writing";
	private static final String ERROR_BUNDLE = "resources/View/Exceptions";
	public ResourceBundle myCommands;
	public ResourceBundle myErrors;
	private Property myProperty;
	private String myTag;
	
	
	/**
	 * @param Property containing all data values desired to be written.
	 */
	public ConfigurationWriter(Property prop) {
		myProperty = prop;
		myTag = myCommands.getString("configWriterTag");
		initializeResources();
	}
	
	private void initializeResources(){
		myCommands = ResourceBundle.getBundle(COMMAND_BUNDLE);
		myErrors = ResourceBundle.getBundle(ERROR_BUNDLE);
	}
	
	/**
	 * Takes in a file and writes the current configuration settings to it.
	 * 
	 * @param file File to which configuration settings are written.
	 */
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
		String data = myProperty.getXML(INDENTS + 1);
		return String.format(myCommands.getString("tag"), NO_INDENT, myTag, data, NO_INDENT, myTag);
	}

}
