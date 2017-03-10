package SLogo.Testing;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandlerTester {

	public static void main(String[] args) {
		File file = new File("data/examples/loops/circle.logo");
		try{
			System.out.println(new String(Files.readAllBytes(Paths.get(file.toURI()))));
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
