package ro.sci.tichetssold;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class permits to test the functionality of TichetsManagementSystem
 * class.
 * 
 * @author ovidiu
 *
 */
public class TichetsManagementSystemTest {

	@Test
	public void testSellingTickets() throws SoldoutException {
		// given
		TichetsManagementSystem tms = new TichetsManagementSystem();
		tms.setTotalNumberOfTichetsAndCalculateTichetsPerCategory(20);
		// when
		tms.sellTichetsToClients();
		int nrTichets = 0;
		// tms.getNumberOfTichetsSold - return int[] = tichets sold per category
		for (int i : tms.getNumberOfTicketsSold()) {
			nrTichets += i;
		}
		// then
		assertEquals("number of tickets ", nrTichets, 20);
	}

	@Test
	public void testIfTicketsPerCategory1AreOK() {
		// given
		TichetsManagementSystem tms = new TichetsManagementSystem();
		// when
		tms.setTotalNumberOfTichetsAndCalculateTichetsPerCategory(20);
		// then
		assertEquals("nr tichets categ 1 =ok", 10, (int) tms.getMapOfTichets().get(TichetType.CATEG1));
	}

	@Test
	public void testIfTicketsPerCategory2AreOK() {
		// given
		TichetsManagementSystem tms = new TichetsManagementSystem();
		// when
		tms.setTotalNumberOfTichetsAndCalculateTichetsPerCategory(20);
		// then
		assertEquals("nr tichets categ 2 =ok", 7, (int) tms.getMapOfTichets().get(TichetType.CATEG2));
	}

	@Test
	public void testIfTicketsPerCategory3AreOK() {
		// given
		TichetsManagementSystem tms = new TichetsManagementSystem();
		// when
		tms.setTotalNumberOfTichetsAndCalculateTichetsPerCategory(20);
		// then
		assertEquals("nr tichets categ 3 =ok", 3, (int) tms.getMapOfTichets().get(TichetType.CATEG3));
	}

	@Test
	public void testIfValueOfTichetsSoldIsCalculatedCorrect() {
		// given
		TichetsManagementSystem tms = new TichetsManagementSystem();
		tms.setTotalNumberOfTichetsAndCalculateTichetsPerCategory(20);
		// when
		tms.sellTichetsToClients();
		// then
		assertEquals("value of tichets sold =ok", 1420, tms.calculateAndPrintTheValueOfTichetsSold());
	}

	// @Before
	/*
	 * public void initMapOfTichets() { int totalNumberOfTichets = 50;
	 * mapOfTichets.put(price.RON50, (int) Math.round(totalNumberOfTichets *
	 * 0.5)); mapOfTichets.put(price.RON80, (int)
	 * Math.round(totalNumberOfTichets * 0.35)); mapOfTichets.put(price.RON120,
	 * totalNumberOfTichets - mapOfTichets.get(price.RON50) -
	 * mapOfTichets.get(price.RON80)); //price.valueOf(enumType, ron50);
	 * 
	 * assertEquals("map populated ok", totalNumberOfTichets,
	 * mapOfTichets.get(price.RON50) + mapOfTichets.get(price.RON80) +
	 * mapOfTichets.get(price.RON120)); }
	 */

}
