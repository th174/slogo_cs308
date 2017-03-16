package SLogo.FileHandling;

/**
 * This interface should be implemented by any class representing a
 * data structure whose data needs to be translated into XML format.
 *
 * @author Stone Mathers
 *         Created 3/10/2017
 */
public interface Property {

    /**
     * Creates an XML-formatted String of the Property's data.
     *
     * @param tabs The number of tabs over at which the line begins.
     * @return String of data in XML format.
     */
    String getXML(int tabs);

}
