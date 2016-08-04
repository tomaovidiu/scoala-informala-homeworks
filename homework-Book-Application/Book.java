import java.util.*;
/**
The book class is the super class of classes Novels and ArtAlbums. Every object of this class has the folowing states: 

*The methods displayBook() and createBook() are both overrided in clases Novels and ArtAlbums.

*/
public class Book{
	protected int nrPagesOfBook; 
	protected String nameOfBook;
	

	
public Book (String nameOfBook, int nrPagesOfBook){
		
		this.nameOfBook = nameOfBook;
		this.nrPagesOfBook = nrPagesOfBook;
}
/**
*This method list a book from the libray. It's used in listBook method. In clases Novels and ArtAlbums this method is overrided.
*/

public String toString()
	{
	return "\nName: " + nameOfBook + "\nNumber of pages: "+ nrPagesOfBook;
	}
}