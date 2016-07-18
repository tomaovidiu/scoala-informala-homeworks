package ro.sci.electingapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Implement a mayor - electing application. Every time a citizen votes, they
 * will need to enter their personal details(CNP, Name) and candidate they are
 * voting for. Every vote is saved as a new line in a file (votes.txt) in the
 * following format: CNP, Name of voter, Name of candidate.
 * 
 * Once the voting is done, the vote counting process is started.
 * 
 * Every line from the file is read and the following rules are applied to the
 * votes: If someone tries to vote twice (CNP shows up more than once) they
 * receive a fine and all of their votes are invalidated. If someone fails to
 * choose a candidate, their vote is invalidated.
 * 
 * All valid votes are counted. Candidate with highest number of votes is
 * elected as mayor.
 * 
 * UPDATE Reuse the mayor-electing homework and extend the program with the
 * following functionality.
 * 
 * We should try to simulate the real-time scenarios in voting conditions. Some
 * users might take more time to decide / read through the leaflet / find the
 * desired candidate.
 * 
 * Do this by implementing the following: For every vote, start a Thread
 * (voter-thread). Let each thread sleep for a random nr of seconds (between 1
 * and 3 seconds). Once the Thread is awake again, write the vote into the file.
 * Make sure you open and close the file each time you vote.
 * 
 * Implement a special thread (counter-thread) that wakes up every 5 seconds,
 * reads the votes out of the file and prints the current election standings.
 * 
 * Make sure the voter-threads and the counter-thread are synchronized, to make
 * sure no one is trying to write into the voting file while another person is
 * also trying to write to it, or the counter is trying to read it.
 * 
 * @author Ovidiu
 */

public class Main {

	private static int threadId = 0;

	public static void main(String[] args) throws Exception {
		manageExpenseRecordsAsync();
	}

	private static void manageExpenseRecordsAsync() {
		// empty file
		TXTCitizenRepository repo = new TXTCitizenRepository();
		try {
			repo.emptyTXT();
		} catch (IOException e) {
			e.printStackTrace();
		}
		showReportsAsync(repo);
		addCitizenAsync(repo, new Citizen("Dick Dastardly", "1982037357397", "Muttley"));
		addCitizenAsync(repo, new Citizen("Burt Reynolds", "2909763891982", "Mr. Bean"));
		addCitizenAsync(repo, new Citizen("Ronaldo", "0977187368992", "Spirt Mona"));
		addCitizenAsync(repo, new Citizen("Britney Spears", "8889097263791", "A tree"));
		addCitizenAsync(repo, new Citizen("Stevie Wonder", "9555682954009", ""));
		addCitizenAsync(repo, new Citizen("Dick Dastardly", "1982037357397", "Muttley"));
		addCitizenAsync(repo, new Citizen("Scooby Doo", "6789293661803", "Mr. Bean"));

	}

	private static void addCitizenAsync(TXTCitizenRepository repo, Citizen citizen) {
		new Thread("add-citizen-thread- " + threadId++) {
			@Override
			public void run() {
				try {
					Thread.sleep(1000 + new Random().nextInt(2000));
					synchronized (repo) {
						CitizenRecords records = new CitizenRecords(repo);
						records.addCitizen(citizen);
						Date date = new Date();
						System.out.println("At " + date.toString() + " voted: " + citizen.toString());
					}
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}.start();
	}

	private static void showReportsAsync(TXTCitizenRepository repo) {
		new Thread("reports-citizen-thread") {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(5000);
						synchronized (repo) {
							CitizenRecords records = new CitizenRecords(repo);
							ArrayList<Citizen> listOfCitizenFromRepo = new ArrayList<>();
							listOfCitizenFromRepo = repo.loadAll();
							// print report on console
							records.printReport(listOfCitizenFromRepo);
						}
					}
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}.start();
	}
}