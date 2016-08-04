package ro.sci.tichetssold;

import java.util.HashMap;

/**
 * * Sa se scrie o aplicatie care simuleaza vanzarea de bilete prin urmatoriul
 * scenariu: - exista N+13 doritori - vanzarea de bilete incepe cu categoria 3 -
 * fiecare al 17-lea cumparator va cumpara cate 3 bilete - de fiecare data cand
 * se vinde un bilet, se afiseaza la consola: categoria, pretul biletului si
 * cate bilete au mai ramas libere pe categoria respectiva - odata ce biletele
 * de la o categore inferioara s-au epuizat, se va arunca un custom checked
 * exception "CategoryTicketsSoldoutException" cu mesajul
 * "Nu mai sunt bilete la categoria X." unde X este categoria de unde se
 * incearca cumparea, se va tipari la consola mesajul exceptiei, si se vor vinde
 * auovidiut tikete din categoria urmatoare pana cand se vor epuiza si acestea,
 * iar in momentul in care s-au epuizat toate biletele din toate categoriile se
 * vaarunca runtime exception "SoldoutException" cu mesajul
 * "Toate bilete au fost vandude."
 * 
 * @author ovidiu
 *
 */
public class TichetsManagementSystem implements TichetsManagementInterface {
	private int numberOfBuyers;
	private int totalNumberOfTichets;
	private HashMap<TichetType, Integer> mapOfTichets = new HashMap<TichetType, Integer>();
	private int[] numberOfTicketsSold = new int[3]; // number of tickets sold
												// per category
	private boolean[] alreadyThrowExceptionForCategory = new boolean[2];
	// is necessary only for category 1 and 2

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ro.sci.tichetssold.TichetsManagementInterface#sellTichetsToAllClients()
	 */
	@Override
	public void sellTichetsToClients() {
		calculateInitialNumberOfTichetsPerCategory();
		numberOfBuyers = getTotalNumberOfTichets() + 13;
		for (int i = 1; i < numberOfBuyers; i++) {
			//// se verifica daca este al 17 cumparator; fiecare al 17 lea
			//// cumpara 3 tichete
			try {
				if (i % 17 == 0) {
					sellATichet();
					sellATichet();
					sellATichet();
				} else {
					sellATichet();
				}
			} catch (CategoryTicketsSoldoutException e) {
				e.printStackTrace();
			} catch (SoldoutException e) {
				e.printStackTrace();
			}
		}
	}

	int calculateAndPrintTheValueOfTichetsSold() {// package visible for the
													// test class
		System.out.println();
		int valueOfTichetsSold = 0;
		System.out.println("------------");
		for (int i = 0; i < 3; i++) {
			System.out.println("CATEG" + (i + 1) + " Numar tichete vandute:" + numberOfTicketsSold[i]
					+ " Valoare tichete vandute:" + TichetType.values()[i].getPret() * numberOfTicketsSold[i]);
			valueOfTichetsSold += TichetType.values()[i].getPret() * numberOfTicketsSold[i];
		}
		System.out.println("Valoare totala tichete vandute:" + valueOfTichetsSold);
		return valueOfTichetsSold;
	}

	private void calculateInitialNumberOfTichetsPerCategory() {
		mapOfTichets.put(TichetType.CATEG1, (int) Math.round(getTotalNumberOfTichets() * 0.50));
		mapOfTichets.put(TichetType.CATEG2, (int) Math.round(getTotalNumberOfTichets() * 0.35));
		mapOfTichets.put(TichetType.CATEG3, (int) (getTotalNumberOfTichets() - mapOfTichets.get(TichetType.CATEG1)
				- mapOfTichets.get(TichetType.CATEG2)));
	}

	private void sellATichet() throws CategoryTicketsSoldoutException, SoldoutException {
		// check if a exception is already throw for category 1 and 2

		if (mapOfTichets.get(TichetType.CATEG1) == 0 && !alreadyThrowExceptionForCategory[0]) {
			new CategoryTicketsSoldoutException("!!!Nu mai sunt bilete la categoria 1");
			alreadyThrowExceptionForCategory[0] = true;
		}
		if (mapOfTichets.get(TichetType.CATEG2) == 0 && !alreadyThrowExceptionForCategory[1]) {
			new CategoryTicketsSoldoutException("!!!Nu mai sunt bilete la categoria 2");
			alreadyThrowExceptionForCategory[1] = true;
		}
		if (mapOfTichets.get(TichetType.CATEG3) == 0) {
			calculateAndPrintTheValueOfTichetsSold();
			new SoldoutException("!!!Nu mai sunt DELOC bilete");
		}

		// sell ticket from the category where are available tickets
		if (mapOfTichets.get(TichetType.CATEG1) > 0) {
			sellATichetFromCategory(TichetType.CATEG1);
			numberOfTicketsSold[0]++;
		} else if (mapOfTichets.get(TichetType.CATEG2) > 0) {
			sellATichetFromCategory(TichetType.CATEG2);
			numberOfTicketsSold[1]++;
		} else if (mapOfTichets.get(TichetType.CATEG3) > 0) {
			sellATichetFromCategory(TichetType.CATEG3);
			numberOfTicketsSold[2]++;
		}
	}

	private void sellATichetFromCategory(TichetType categ) {
		if (mapOfTichets.get(categ) > 0) {
			mapOfTichets.put(categ, mapOfTichets.get(categ) - 1);
			System.out.println("Categoria:" + categ + "  Pret bilet:" + categ.getPret() + "  Numar tichete ramase: "
					+ mapOfTichets.get(categ));
		}
	}

	public int getTotalNumberOfTichets() {
		return totalNumberOfTichets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ro.sci.tichetssold.TichetsManagementInterface#setTotalNumberOfTichets(
	 * int)
	 */
	@Override
	public void setTotalNumberOfTichetsAndCalculateTichetsPerCategory(int totalNumberOfTichets) {
		this.totalNumberOfTichets = totalNumberOfTichets;
		calculateInitialNumberOfTichetsPerCategory();
	}

	public int[] getNumberOfTicketsSold() {
		return numberOfTicketsSold;
	}

	public HashMap<TichetType, Integer> getMapOfTichets() {
		return mapOfTichets;
	}
}
