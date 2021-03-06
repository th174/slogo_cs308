package SLogo.Testing;

import SLogo.FileHandling.ConfigurationWriter;
import SLogo.Turtles.ObservableTurtle;

import java.io.File;

/**
 * Tester class used to ensure that Configuration Files are being correctly written in XML format.
 * 
 * @author Stone Mathers
 */
public class ConfigurationWriterTester {

    public static void main(String[] args) {
        ObservableTurtle turt = new ObservableTurtle(3);
        turt.setXY(15, 10);
        turt.setHeading(50);
        turt.setPenDown(false);

        ConfigurationWriter cw = new ConfigurationWriter(turt.toProperty());

        cw.write(new File("data/ConfigWriterTestFile1.txt"));

    }

}
