package SLogo.Parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The purpose of this class is to obtain a file from the user, then return a String containing 
 * the commands that the file holds. It first creates and displays a FileChooser in the given
 * Scene, which prompts the user to select a file, then returns the commands as a String.
 * 
 * @author Stone Mathers
 * Created 3/9/2017
 */
public class FileHandler {
	
	private static final String ERROR_BUNDLE = "resources/View/Exceptions";
	public ResourceBundle myResources = ResourceBundle.getBundle(ERROR_BUNDLE);
	private Stage myStage;
	
	/**
	 * 
	 */
	public FileHandler(Stage stage) {
		myStage = stage;
	}
	
	public String getFile(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select SLogo File");
		return readFile(chooser.showOpenDialog(myStage));
	}
	
	private String readFile(File file){
		StringBuilder commands = new StringBuilder();
		String nextLine = "";
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while((nextLine=reader.readLine()) != null){
				commands.append("\n" + nextLine);
			}
			reader.close();
			return commands.toString();
		}catch(FileNotFoundException e){
			throw new RuntimeException(String.format(myResources.getString("FileNotFound"), file.getName()));
		}catch(Exception e){
			throw new RuntimeException(String.format(myResources.getString("FileReadingError"), file.getName()));
		}
	}
}
