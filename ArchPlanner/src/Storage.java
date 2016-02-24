import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class specifies the process of accessing, updating and saving of data to a file.
 * @author riyang
 *
 */
public class Storage {

	//This is the name of the file to be manipulated
	public static String _fileName;

	//This the File to be manipulated by the program
	public static File _file;

	//These are the error messages
	private static final String ERROR_MESSAGE_FILE_CREATION_FAILED = "File creation failed!";
	private static final String ERROR_MESSAGE_FILE_LOAD_FAILED = "Load file failed!";

	//This arraylist is used to store the strings of text in the file
	private static ArrayList<Task> tasksList = new ArrayList<Task>();

	private static void loadStorageFile() throws IOException {
		_fileName = "Storage.srl";
		_file = new File(_fileName);

		if (!_file.exists() || !_file.isFile()) {
			createStorageFile();
		} else {
			FileOutputStream fos = new FileOutputStream("Storage.srl");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tasksList);
			oos.close();
		}
	}

	private static void createStorageFile() throws IOException {
		_file.createNewFile();
	}
}
