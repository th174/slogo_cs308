package SLogo.FileHandling;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Stone Mathers
 * Created 3/10/2017
 */
public class PropertyMap implements Property {

	private static final String COMMAND_BUNDLE = "resources/files/writing";
	public ResourceBundle myCommands = ResourceBundle.getBundle(COMMAND_BUNDLE);
	Map<String, Property> myMap;
	
	public PropertyMap(Map<String, Property> valueMap) {
		myMap = valueMap;
	}

	@Override
	public String getXML(int tabs) {
		StringBuilder data = new StringBuilder();
		String indents = makeIndents(tabs);
		
		for(String tag: myMap.keySet()){
			data.append(String.format(myCommands.getString("tag"), indents, tag, myMap.get(tag).getXML(tabs + 1), indents, tag));
		}
		
		return data.toString();
	}

	/**
	 * Creates a String containing the given number of tabs.
	 * 
	 * @param tabs Number of tabs to print
	 * @return String of tabs
	 */
	private String makeIndents(int tabs){
		String indents = "";
		for(int i = 0; i < tabs; i++){
			indents+="\t";
		}
		return indents;
	}
}
