package ro.sci.electingapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This interface defines the file name from where the citizens will be save /
 * load. It also defines the delimiter of data in file. It has 5 unimplemented
 * methods:
 * <ul>
 * <li>save - save a citizen to file
 * <li>loadAll - load all the citizens from file
 * <li>openTXT - open the TXT file
 * <li>emptyTXT - empty the TXT file
 * <li>close - close the file
 * </ul>
 * 
 * @author Ovidiu
 *
 */
public interface ElectingAppInterface {
	static final String TXT_FILE_NAME = "votes.txt";
	static final String COMMA = ",";

	/**
	 * The method save a Citizen to file.
	 * 
	 * @param citizen
	 * @throws Exception
	 */
	void save(Citizen citizen) throws Exception;

	/**
	 * The method load all the citizens from file.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	ArrayList<Citizen> loadAll() throws FileNotFoundException, IOException;

	/**
	 * The method open TXT file.
	 * 
	 * @throws IOException
	 */
	void openTXT() throws IOException;

	/**
	 * The method empty the contents of file.
	 * 
	 * @throws IOException
	 */
	void emptyTXT() throws IOException;

	/**
	 * The method close the file.
	 * 
	 * @throws Exception
	 */
	void close() throws Exception;

}
