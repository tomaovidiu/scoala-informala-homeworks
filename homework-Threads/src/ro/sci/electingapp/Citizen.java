package ro.sci.electingapp;

/**
 * Class of Citizen. A citizen has 3 fields:
 * <ul>
 * <li>Name
 * <li>Cnp
 * <li>Elected Candidate.
 * </ul>
 * 
 * @author Ovidiu
 *
 */
public class Citizen {
	private String cnp;
	private String name;
	private String candidateElected;

	public Citizen(String name, String cnp, String candidateElected) {
		super();
		this.cnp = cnp;
		this.name = name;
		this.candidateElected = candidateElected;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCandidateElected() {
		return candidateElected;
	}

	public void setCandidateElected(String candidateElected) {
		this.candidateElected = candidateElected;
	}

	@Override
	public String toString() {
		return name + ", " + cnp + ", " + candidateElected;
	}
}
