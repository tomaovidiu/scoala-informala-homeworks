package ro.sci.electingapp;

import java.util.ArrayList;

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
 * @author Ovidiu
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {

		try (TXTCitizenRepository repo = new TXTCitizenRepository()) {
			repo.emptyTXT(); // empty file
			
			// add citizens to repo. They are added one by one.
			CitizenRecords records = new CitizenRecords(repo);
			records.addCitizen(new Citizen("Dick Dastardly", "1982037357397", "Muttley"));
			records.addCitizen(new Citizen("Burt Reynolds", "2909763891982", "Mr. Bean"));
			records.addCitizen(new Citizen("Ronaldo", "0977187368992", "Spirt Mona"));
			records.addCitizen(new Citizen("Britney Spears", "8889097263791", "A tree"));
			records.addCitizen(new Citizen("Stevie Wonder", "9555682954009", ""));
			records.addCitizen(new Citizen("Dick Dastardly", "1982037357397", "Muttley"));
			records.addCitizen(new Citizen("Scooby Doo", "6789293661803", "Mr. Bean"));

			ArrayList<Citizen> listOfCitizenFromRepo = new ArrayList<>();
			// read citizens from repo. They are read all - together.
			listOfCitizenFromRepo = repo.loadAll();

			// print report on console
			records.printReport(listOfCitizenFromRepo);
		}
	}
}