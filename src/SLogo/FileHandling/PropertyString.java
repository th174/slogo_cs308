package SLogo.FileHandling;

/**
 * @author Stone Mathers
 * Created 3/10/2017
 */
public class PropertyString implements Property {

	String myString;
	

	public PropertyString(String value) {
		myString = value;
	}


	@Override
	public String getXML(int tabs) {
		String indents = "";
		for(int i = 0; i < tabs; i++){
			indents+="\t";
		}
		return (indents + myString);
	}

}
