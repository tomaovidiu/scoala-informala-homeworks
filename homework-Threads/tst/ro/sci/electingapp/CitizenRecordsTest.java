package ro.sci.electingapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is testing the CitizenRecods class.
 * 
 * @author Ovidiu
 *
 */
public class CitizenRecordsTest {
	ArrayList<Citizen> listOfCitizen = new ArrayList<>();

	@Before
	public void initializeListOfCitizenForTests() {
		listOfCitizen.add(new Citizen("Dick Dastardly", "1982037357397", "Muttley"));
		listOfCitizen.add(new Citizen("Dick Dastardly", "1982037357397", "Muttley"));
		listOfCitizen.add(new Citizen("Burt Reynolds", "2909763891982", "Mr. Bean"));
		listOfCitizen.add(new Citizen("Ronaldo", "0977187368992", "Spirt Mona"));
		listOfCitizen.add(new Citizen("Britney Spears", "8889097263791", "A tree"));
		listOfCitizen.add(new Citizen("Stevie Wonder", "9555682954009", " "));
		listOfCitizen.add(new Citizen("Scooby Doo", "6789293661803", "Mr. Bean"));
		assertEquals("list of citizen created = ok", 7, listOfCitizen.size(), 0);
	}

	@Test
	public void verifyListOfCitizensWhoHaveInvalidVote() {
		// given
		CitizenRecords citizenRecords = new CitizenRecords();
		HashMap<String, ArrayList<Citizen>> mapOfVotes = new HashMap<>();
		// when
		mapOfVotes = citizenRecords.generateVoteListsOfCitizens(listOfCitizen);
		String name = new String();
		for (Citizen citizen : mapOfVotes.get("invalid")) {
			name = name + citizen.getName() + " ";
		}
		// then
		assertEquals("Dick Dastardly ", name);
	}

	@Test
	public void verifyListOfCitizensWhoHaveValidVote() {
		// given
		CitizenRecords citizenRecords = new CitizenRecords();
		HashMap<String, ArrayList<Citizen>> mapOfVotes = new HashMap<>();
		// when
		mapOfVotes = citizenRecords.generateVoteListsOfCitizens(listOfCitizen);
		String names = new String();
		for (Citizen citizen : mapOfVotes.get("valid")) {
			names = names + citizen.getName() + ",";
		}
		// then
		assertEquals("Burt Reynolds,Ronaldo,Britney Spears,Scooby Doo,", names);
	}

	@Test
	public void verifyNrOfInvalidVotes() {
		// given
		CitizenRecords citizenRecords = new CitizenRecords();
		// when
		int actual = citizenRecords.computeNrOfInvalidVotes(listOfCitizen);
		// then
		assertEquals("nr of invalid votes = ok", 3, actual);
	}

	@Test
	public void verifyNrOfValidVotes() {
		// given
		CitizenRecords citizenRecords = new CitizenRecords();
		HashMap<String, ArrayList<Citizen>> mapOfVotes = new HashMap<>();
		// when
		mapOfVotes = citizenRecords.generateVoteListsOfCitizens(listOfCitizen);
		int actualNrOfValidVotes = mapOfVotes.get("valid").size();
		// then
		assertEquals("nr of valid votes = ok", 4, actualNrOfValidVotes);
	}

	@Test
	public void verifyTotalNrOfVotesVsValidAndInvalidVotes() {
		// given
		CitizenRecords citizenRecords = new CitizenRecords();
		HashMap<String, ArrayList<Citizen>> mapOfVotes = new HashMap<>();
		// when
		mapOfVotes = citizenRecords.generateVoteListsOfCitizens(listOfCitizen);
		int actualNrOfValidVotes = mapOfVotes.get("valid").size();
		int actualNrOInvalidVotes = citizenRecords.computeNrOfInvalidVotes(listOfCitizen);
		// then
		assertEquals("nr of total votes =ok ", listOfCitizen.size(), actualNrOfValidVotes + actualNrOInvalidVotes);
	}

	@Test
	public void verifyElectedMayor() {
		// given
		CitizenRecords citizenRecords = new CitizenRecords();
		HashMap<String, ArrayList<Citizen>> mapOfVotes = new HashMap<>();
		// when
		mapOfVotes = citizenRecords.generateVoteListsOfCitizens(listOfCitizen);
		ArrayList<Citizen> listOfCitizenWithValidVotes = mapOfVotes.get("valid");
		HashMap<String, Integer> mapOfMayor = citizenRecords.computeMapOfMayor(listOfCitizenWithValidVotes);
		String string = citizenRecords.computeElectedMayor(mapOfMayor);
		// then
		assertEquals("Mr. Bean", string);
	}

	@Test
	public void verifyPercentageObtainOfMayors() {
		// given
		HashMap<String, Float> expectedPercentageVoteOfMayor = new HashMap<>();
		expectedPercentageVoteOfMayor.put("Mr. Bean", 50f);
		expectedPercentageVoteOfMayor.put("A tree", 25f);
		expectedPercentageVoteOfMayor.put("Spirt Mona", 25f);

		CitizenRecords citizenRecords = new CitizenRecords();
		HashMap<String, ArrayList<Citizen>> mapOfVotes = new HashMap<>();
		mapOfVotes = citizenRecords.generateVoteListsOfCitizens(listOfCitizen);
		ArrayList<Citizen> listOfCitizenWithValidVotes = mapOfVotes.get("valid");

		int totalNrOfVotes = listOfCitizenWithValidVotes.size();
		HashMap<String, Integer> mapOfMayor = citizenRecords.computeMapOfMayor(listOfCitizenWithValidVotes);
		HashMap<String, Float> actualPercentageVoteOfMayor = new HashMap<>();
		// when
		actualPercentageVoteOfMayor = citizenRecords.computePercentageOfVotesForEveryCandidate(mapOfMayor,
				totalNrOfVotes);
		boolean b = TestUtils.compareMap(actualPercentageVoteOfMayor, expectedPercentageVoteOfMayor);
		// then
		assertTrue(b);
	}
}
