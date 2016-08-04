package ro.sci.tichetssold;

/**
 * The tickets Management Interface
 * 
 * @author ovidiu
 *
 */
public interface TichetsManagementInterface {

	/**
	 * The method sell tickets to clients.
	 * 
	 * @throws SoldoutException
	 */
	void sellTichetsToClients();

	/**
	 * The method set the total number of tickets to be sold. Also in this
	 * method the tickets are divided in 3 category.
	 * 
	 * @param totalNumberOfTichets
	 */
	void setTotalNumberOfTichetsAndCalculateTichetsPerCategory(int totalNumberOfTichets);

}