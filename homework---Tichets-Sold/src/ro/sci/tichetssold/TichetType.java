package ro.sci.tichetssold;

public enum TichetType {
	CATEG1 (50),
	CATEG2 (80),
	CATEG3 (120);

	private final int pret;

TichetType (int pret)
{
this.pret=pret;
}

public int getPret() {
	return pret;
}

}