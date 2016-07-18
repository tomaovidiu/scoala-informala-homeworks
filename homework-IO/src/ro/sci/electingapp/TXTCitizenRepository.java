package ro.sci.electingapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class permits save and load of citizens who vote on a TXT file.
 * 
 * @author Ovidiu
 *
 */
public class TXTCitizenRepository implements AutoCloseable, ElectingAppInterface {
	private FileWriter writer;
	private File file;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ro.sci.electingapp.ElectingAppInterface#save(ro.sci.electingapp.Citizen)
	 */
	@Override
	public void save(Citizen citizen) throws Exception {
		openTXT();
		writer.write(citizen.toString() + "\n");
		close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.electingapp.ElectingAppInterface#loadAll()
	 */
	@Override
	public ArrayList<Citizen> loadAll() throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			ArrayList<Citizen> citizenList = new ArrayList<>();
			String serializedCitizen;
			while ((serializedCitizen = reader.readLine()) != null) {
				String[] tokens = serializedCitizen.split(COMMA);
				citizenList.add(new Citizen(tokens[0], tokens[1], tokens[2]));
				System.out.println("Citizen added from TXT! " + tokens[0]);
			}
			return citizenList;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.electingapp.ElectingAppInterface#openTXT()
	 */
	@Override
	public void openTXT() throws IOException {
		file = new File(TXT_FILE_NAME);
		writer = new FileWriter(file, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.electingapp.ElectingAppInterface#emptyTXT()
	 */
	@Override
	public void emptyTXT() throws IOException {
		file = new File(TXT_FILE_NAME);
		writer = new FileWriter(file, false);
		writer.write("");
		writer.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() throws IOException {
		if (!(writer == null))
			writer.close();
	}
}
