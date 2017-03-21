package SLogo.Testing;

import java.io.File;
import java.util.HashMap;

import SLogo.FileHandling.ConfigurationWriter;
import SLogo.FileHandling.Property;
import SLogo.FileHandling.PropertyMap;
import SLogo.Turtles.ObservableTurtle;

/**
 * Tester class used to ensure that Configuration Files are being correctly written in XML format.
 * 
 * @author Stone Mathers
 */

public class ConfigurationWriterTester {

	public static void main(String[] args) {
		HashMap<String, Property> turtMap = new HashMap<String, Property>();
		
		ObservableTurtle turt1 = new ObservableTurtle(3);
		turt1.setXY(15, 10);
		turt1.setHeading(50);
		turt1.setPenDown(false);
		ObservableTurtle turt2 = new ObservableTurtle(3);
		turt2.setXY(-20, 1);
		turt2.setHeading(100);
		turt2.setPenDown(true);
		
		turtMap.put("turt1", turt1.toProperty());
		turtMap.put("turt2", turt2.toProperty());
		
		ConfigurationWriter cw = new ConfigurationWriter(new PropertyMap(turtMap));
		
		cw.write(new File("data/ConfigWriterTestFile1.txt"));

	}

}
