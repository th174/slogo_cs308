package SLogo.FileHandling;

import java.io.File;

/**
 * The general purpose of a class implementing this interface is to write data to a file.
 * The data to be written and formatting rules of the file are determined by any implementing
 * class. 
 * 
 * @author Stone Mathers
 * Created 3/10/2017
 */
public interface FileWriter {

	/**
	 * Takes in a file and writes data to it.
	 * 
	 * @param file File to which data is written.
	 */
	void write(File file);

}
