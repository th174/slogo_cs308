package SLogo.FileHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * Obtains a file from the user, then return a String containing 
 * the commands that the file holds. It first creates and displays a FileChooser in the given
 * Scene, which prompts the user to select a file, then returns the commands as a String.
 * 
 * @author Stone Mathers
 * Created 3/9/2017
 */
public class FileHandler {
	
	private static final String ERROR_BUNDLE = "resources/View/Exceptions";
	public ResourceBundle myResources = ResourceBundle.getBundle(ERROR_BUNDLE);
	private Window myWindow;
	
	public FileHandler(Window userWindow) {
		myWindow = userWindow;
	}
	
	public String getFileData(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select SLogo File");
		return readFile(chooser.showOpenDialog(myWindow));
	}
	
	public String readFile(File file){
		try{
			return new String((Files.readAllBytes(Paths.get(file.toURI()))));
		}catch(IOException e){
			throw new RuntimeException(String.format(myResources.getString("FileReadingError"), file.getName()));
		}
	}
}
