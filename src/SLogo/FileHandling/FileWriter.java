package SLogo.FileHandling;

import java.io.File;

/**
 * @author Stone Mathers
 *
 */
public interface FileWriter {

	/**
	 * Takes in a file and writes data to it.
	 * 
	 * @param file File to which data is written.
	 */
	void write(File file);

}
