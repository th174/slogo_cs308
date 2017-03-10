package SLogo.FileHandling;

/**
 * @author Stone Mathers
 * Created 3/10/2017
 */
public interface Property {
	
	/**
	 * Creates an XML-formatted String of the Property's data.
	 * 
	 * @param tabs The number of tabs over at which the line begins.
	 * @return
	 */
	String getXML(int tabs);
	
}
