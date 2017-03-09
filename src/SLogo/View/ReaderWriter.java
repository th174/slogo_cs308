package SLogo.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;

public class ReaderWriter<T> {

	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "ReaderWriter";
	private ResourceBundle myResources;
	
	public ReaderWriter() {
		initializeResources();
	}

	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	public void writeObject(T object) throws FileNotFoundException, Exception {
    	File file = new File(myResources.getString("SavePath"));  //creating buffer streams to student file
        FileOutputStream fileOutStream = new FileOutputStream(file);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutStream);
        objOutputStream.writeObject(object);  //writing student to file
        objOutputStream.close();  //closing streams
        fileOutStream.close();
	}
	
	@SuppressWarnings("unchecked")
	public T readObject() throws FileNotFoundException, Exception {
		T object = null;
        File file = new File(myResources.getString("SavePath"));  //creating buffer streams to list file
        FileInputStream fileInStream = new FileInputStream(file);
        ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
        object = (T)objInStream.readObject();  //reading list from file and putting it in the array
        objInStream.close();  //closing streams
        fileInStream.close(); 
	    return object;  //returning read array value
	}
}
