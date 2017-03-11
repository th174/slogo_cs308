package SLogo.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
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
	public ResourceBundle myResources;
	private Window myWindow;
	
	public FileHandler(Window userWindow) {
		myWindow = userWindow;
		initializeResources();
	}
	
	/**
	 * Prompts user to select a file, then reads the chosen file 
	 * and returns its contents as a String.
	 * 
	 * @return String of data contained within the file.
	 */
	public String getFileData(String title){
		try{
			return readFile(getFile(title));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Prompts user to select a file and returns whichever file is chosen.
	 * 
	 * @return User-selected File
	 */
	public File getFile(String title) throws FileNotFoundException{
		FileChooser chooser = new FileChooser();
		chooser.setTitle(title);
		File chosen = chooser.showOpenDialog(myWindow);
		if(chosen != null){
			return chosen;
		}else{
			throw new FileNotFoundException();
		}
	}
	
	/**
	 * Prompts user to select a file and returns whichever file is chosen.
	 * 
	 * @return User-selected File
	 */
	public File saveNewFile(String title) throws FileNotFoundException{
		FileChooser chooser = new FileChooser();
		chooser.setTitle(title);
		File chosen = chooser.showSaveDialog(myWindow);
		if(chosen != null){
			return chosen;
		}else{
			throw new FileNotFoundException();
		}
	}
	
	private void initializeResources(){
		myResources = ResourceBundle.getBundle(ERROR_BUNDLE);
	}
	
	private String readFile(File file){
		try{
			return new String((Files.readAllBytes(Paths.get(file.toURI()))));
		}catch(IOException e){
			throw new RuntimeException(String.format(myResources.getString("FileReadingError"), file.getName()));
		}
	}
}
