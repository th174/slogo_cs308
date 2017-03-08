package SLogo.View;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.scene.paint.Color;

public class XMLParser {
	
	public static void populateMaps(Map<Double,Color> colorMap, Map<Double,File> imageMap, String XMLFilename) {
		File mapPropertiesFile = new File(XMLFilename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(mapPropertiesFile);
			System.out.println("yo");
			NodeList colors = doc.getElementsByTagName("Color");
			for (int i = 0; i < colors.getLength(); i++){
				String RGB = colors.item(i).getTextContent();
				String[] rgbValue_str =  RGB.split(",");
				int[] rgbValue = new int[3];
				for (int j = 0; j < 3; j++){
					rgbValue[j] = Integer.parseInt(rgbValue_str[j]);
				}
				colorMap.put((double) i, Color.rgb(rgbValue[0], rgbValue[1], rgbValue[2]));
			}
			NodeList images = doc.getElementsByTagName("Image");
			for (int i = 0; i < images.getLength(); i++){
				String filenameValue = images.item(i).getTextContent();
				imageMap.put((double) i, new File(filenameValue));
			}
		} catch (ParserConfigurationException e) {
			new ErrorPrompt("MapProperties XML file not formatted correctly");
		} catch (SAXException e) {
			new ErrorPrompt("MapProperties XML file not formatted correctly");
		} catch (IOException e) {
			new ErrorPrompt("MapProperties XML file not formatted correctly");
		}
	}
}
