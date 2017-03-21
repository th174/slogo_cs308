package SLogo.FileHandling;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * PropertyMap is a wrapper class for any Map of data that needs to be written into XML format.
 * The Map that it wraps maps String keys to Property values. In XML format, the key is the tag
 * surrounding the Property that it corresponds to.
 *
 * @author Stone Mathers
 *         Created 3/10/2017
 */
public class PropertyMap implements Property {

    private static final String COMMAND_BUNDLE = "resources/files/writing";
    public ResourceBundle myCommands = ResourceBundle.getBundle(COMMAND_BUNDLE);
    Map<String, Property> myMap;

    /**
     * @param valueMap - Map with the tag String as the key and the corresponding Property as the value.
     */
    public PropertyMap(Map<String, Property> valueMap) {
        myMap = valueMap;
    }

    @Override
    public String getXML(int tabs) {
        StringBuilder data = new StringBuilder();
        String indents = makeIndents(tabs);

        for (String tag : myMap.keySet()) {
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
    private String makeIndents(int tabs) {
        String indents = "";
        for (int i = 0; i < tabs; i++) {
            indents += "\t";
        }
        return indents;
    }
}
