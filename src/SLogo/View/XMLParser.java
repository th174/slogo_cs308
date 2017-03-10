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

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class XMLParser {
	
	public void populateMaps(int[] spriteDimensions, Map<Integer,Color> colorMap, Map<Integer,Image> imageMap, String XMLFilename) {
		File mapPropertiesFile = new File(XMLFilename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(mapPropertiesFile);
			spriteDimensions[0] = Integer.parseInt(doc.getElementsByTagName("SpriteWidth").item(0).getTextContent());
			spriteDimensions[1] = Integer.parseInt(doc.getElementsByTagName("SpriteHeight").item(0).getTextContent());
			NodeList colors = doc.getElementsByTagName("Color");
			for (int i = 0; i < colors.getLength(); i++){
				String RGB = colors.item(i).getTextContent();
				String[] rgbValue_str =  RGB.split(",");
				int[] rgbValue = new int[3];
				for (int j = 0; j < 3; j++){
					rgbValue[j] = Integer.parseInt(rgbValue_str[j]);
				}
				colorMap.put(i, Color.rgb(rgbValue[0], rgbValue[1], rgbValue[2]));
			}
			NodeList images = doc.getElementsByTagName("Image");
			for (int i = 0; i < images.getLength(); i++){
				String filenameValue = images.item(i).getTextContent();
				imageMap.put(i, new Image("file:" + filenameValue));
			}
		} catch (ParserConfigurationException e) {
			throw new ErrorPrompt("MapProperties XML file not formatted correctly");
		} catch (SAXException e) {
			throw new ErrorPrompt("MapProperties XML file not formatted correctly");
		} catch (IOException e) {
			throw new ErrorPrompt("MapProperties XML file not formatted correctly");
		}
	}
}
