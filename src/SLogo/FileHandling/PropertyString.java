package SLogo.FileHandling;

/**
 * PropertyString is a wrapper class for any String of data that needs to be written into XML format.
 * In XML format, the String is the data contained within a tag.
 *
 * @author Stone Mathers
 *         Created 3/10/2017
 */
public class PropertyString implements Property {

    String myString;


    public PropertyString(String value) {
        myString = value;
    }


    @Override
    public String getXML(int tabs) {
        String indents = "";
        for (int i = 0; i < tabs; i++) {
            indents += "\t";
        }
        return (indents + myString);
    }

}
