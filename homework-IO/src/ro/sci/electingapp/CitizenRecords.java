package ro.sci.electingapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class is a repository for the citizen who votes. It has 2 public
 * methods:
 * <ul>
 * <li>print report - print report on the console
 * <li>add citizen - add citizen to this repository
 * </ul>
 * 
 * @author Ovidiu
 *
 */
public class CitizenRecords {

	private TXTCitizenRepository repo;

	public CitizenRecords(TXTCitizenRepository repo) {
		this.repo = repo;
	}

	public CitizenRecords() {
	}

	/**
	 * Add a citizen to the repo.
	 * 
	 * @param citizen
	 *            - citizen to be added
	 * @throws Exception
	 */
	public void addCitizen(Citizen citizen) throws Exception {
		repo.save(citizen);
	}

	/**
	 * Print report on console.
	 * 
	 * @param listOfCitizen
	 *            - list of citizens who vote.
	 */
	public void printReport(ArrayList<Citizen> listOfCitizen) {
		HashMap<String, ArrayList<Citizen>> mapOfVotes = new HashMap<>();
		System.out.println("--------------------");
		mapOfVotes = generateVoteListsOfCitizens(listOfCitizen);
		for (Citizen citizen : mapOfVotes.get("invalid")) {
			System.out.print(citizen.getName() + " ");
		}
		System.out.println("has broken the rules and will be fined. All of his votes are invalidated.");

		System.out.print("Invalid Nr. of votes: ");
		System.out.println(computeNrOfInvalidVotes(listOfCitizen));

		System.out.print("Valid Nr. of votes: ");
		ArrayList<Citizen> listOfCitizenWithValidVotes = mapOfVotes.get("valid");
		int totalNrOfVotes = listOfCitizenWithValidVotes.size();
		System.out.println(totalNrOfVotes);

		System.out.print("Elected mayor: ");
		HashMap<String, Integer> mapOfMayor = computeMapOfMayor(listOfCitizenWithValidVotes);
		String string = computeElectedMayor(mapOfMayor);
		System.out.println(string);

		HashMap<String, Float> percentageVoteOfMayor = new HashMap<>();
		percentageVoteOfMayor = computePercentageOfVotesForEveryCandidate(mapOfMayor, totalNrOfVotes);
		printPercentageOfVotesForEveryCandidate(percentageVoteOfMayor);
	}

	private void printPercentageOfVotesForEveryCandidate(Map<String, Float> percentageVoteOfMayor) {
		System.out.println("The percentage of votes every candidate has achieved:");
		percentageVoteOfMayor = sortByComparator(percentageVoteOfMayor);
		for (Entry<String, Float> entry : percentageVoteOfMayor.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue() + "%");
		}
	}

	HashMap<String, Float> computePercentageOfVotesForEveryCandidate(HashMap<String, Integer> mapOfMayor,
			int totalNrOfVotes) {
		HashMap<String, Float> percentageVoteOfMayor = new HashMap<>();
		for (Entry<String, Integer> entry : mapOfMayor.entrySet()) {
			float percentage = entry.getValue() * 100 / totalNrOfVotes;
			percentageVoteOfMayor.put(entry.getKey(), percentage);
		}
		return percentageVoteOfMayor;
	}

	int computeNrOfInvalidVotes(ArrayList<Citizen> listOfCitizen) {
		int nrOfInvalidVotes = 0; // number of citizen with vote null
		ArrayList<Citizen> temp = new ArrayList<>();
		for (Citizen citizen : listOfCitizen) {
			boolean isAlreadyInTheList = false;
			if (citizen.getCandidateElected().equals(" ")) {
				nrOfInvalidVotes++;
			} else {
				for (Citizen citizen2 : temp) {
					if (citizen.getCnp().equals(citizen2.getCnp())) {
						isAlreadyInTheList = true;
					}
				}
				if (!isAlreadyInTheList) {
					temp.add(citizen);
				}
			}
		}
		// listOfCitizen.size()-temp.size() = nr of citizen who votes multiple times 
		return nrOfInvalidVotes + listOfCitizen.size()-temp.size();
	}

	HashMap<String, ArrayList<Citizen>> generateVoteListsOfCitizens(ArrayList<Citizen> listOfCitizen) {
		ArrayList<Citizen> listOfCitizenWithValidVotes = new ArrayList<>();
		ArrayList<Citizen> listOfCitizenWithInvalidVotes = new ArrayList<>();
		HashMap<String, ArrayList<Citizen>> mapOfVotes = new HashMap<>();

		for (Citizen citizen : listOfCitizen) {
			boolean isInList = false;
			if (!citizen.getCandidateElected().equals(" ")) {
				for (int i = 0; i < listOfCitizenWithValidVotes.size(); i++) {
					if (listOfCitizenWithValidVotes.get(i).getCnp().equals((citizen.getCnp()))) {
						isInList = true;
						// remove the citizen from valid list is found another
						// vote from that citizen
						listOfCitizenWithValidVotes.remove(i);
					}
				}
				if (!isInList) {
					listOfCitizenWithValidVotes.add(citizen);
				} else {
					listOfCitizenWithInvalidVotes.add(citizen);
				}
			}
		}
		mapOfVotes.put("valid", listOfCitizenWithValidVotes);
		mapOfVotes.put("invalid", listOfCitizenWithInvalidVotes);
		return mapOfVotes;
	}

	HashMap<String, Integer> computeMapOfMayor(ArrayList<Citizen> listOfCitizenWithValidVotes) {
		HashMap<String, Integer> mayorMap = new HashMap<>();
		// calculates votes for every mayor
		for (Citizen citizen : listOfCitizenWithValidVotes) {
			if (!mayorMap.containsKey(citizen.getCandidateElected())) {
				mayorMap.put(citizen.getCandidateElected(), 1);
			} else {
				int nrOfVotes = mayorMap.get(citizen.getCandidateElected());
				mayorMap.put(citizen.getCandidateElected(), nrOfVotes + 1);
			}
		}
		return mayorMap;
	}

	String computeElectedMayor(HashMap<String, Integer> mayorMap) {
		int maxNrOfVotes = 0;
		String electedMayor = "";
		for (Entry<String, Integer> entry : mayorMap.entrySet()) {
			if (entry.getValue() > maxNrOfVotes) {
				electedMayor = entry.getKey();
				maxNrOfVotes = entry.getValue();
			}
		}
		return electedMayor;
	}

	private static Map<String, Float> sortByComparator(Map<String, Float> unsortMap) {
		List<Entry<String, Float>> list = new LinkedList<Entry<String, Float>>(unsortMap.entrySet());
		// Sorting the list based on Float
		Collections.sort(list, new Comparator<Entry<String, Float>>() {
			@Override
			public int compare(Entry<String, Float> arg0, Entry<String, Float> arg1) {
				if (arg0.getValue() > arg1.getValue())
					return -1;
				if (arg0.getValue() > arg1.getValue())
					return 1;
				return 0;
			}
		});
		// Maintaining insertion order with LinkedList
		Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
		for (Entry<String, Float> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}
