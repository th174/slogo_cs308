package SLogo.FileHandling;

import java.util.Map;

/**
 * Holds all data used for the initial configuration of a SLogo project.
 * 
 * @author Stone Mathers
 * Created 3/10/2017
 */
public class ConfigurationData {

	Map<String, String> myData;
	
	public ConfigurationData(Map<String, String> data){
		myData = data;
	}
	
}
