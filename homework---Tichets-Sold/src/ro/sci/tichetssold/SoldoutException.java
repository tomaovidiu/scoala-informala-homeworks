package ro.sci.tichetssold;

public class SoldoutException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SoldoutException(String string) {
		super(string);
		System.out.println(string);
	}

}
