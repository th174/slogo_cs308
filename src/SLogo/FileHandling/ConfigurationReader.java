package SLogo.FileHandling;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author Stone Mathers
 * Created 3/10/2017
 */
public class ConfigurationReader {

	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static final String COMMAND_BUNDLE = "resources/files/writing";
	public ResourceBundle myCommands = ResourceBundle.getBundle(COMMAND_BUNDLE);
	
	public ConfigurationReader() {
		// TODO Auto-generated constructor stub
	}
	
	public ConfigurationData getConfiguration(File file){
		Element root = getRootElement(file);		 
		HashMap<String, String> data = new HashMap<String, String>();
		
		//TODO parse data and place in data Map
		
		return new ConfigurationData(data);
	}

	/**
     * Gets the root element of the given XML file.
     * 
     * @param xmlFile
     * @return Element
     */
    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * Creates a DocumentBuilder.
	 * 
	 * @return DocumentBuilder
	 */
	private static DocumentBuilder getDocumentBuilder () {
		try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
	}
	
}
