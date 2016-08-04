package ro.sci.tichetssold;

public class CategoryTicketsSoldoutException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoryTicketsSoldoutException(String string) {
		super(string);
		System.out.println(string);

	}

}
