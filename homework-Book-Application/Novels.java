/**
The class Novels is derived from the Book class. This class is a template for Novels objects. 
It has 1 method and 1 constuctor.
*/

public class Novels extends Book { // subclass of Book; ArtAlbums is a derived class
protected String typeBook;

/**
The constructor of the class has 3 parameters:
*@param nameOfBook - contains the name of the object ArtAlbums which wil be created.
*@param nrPagesOfBook - contains the number of pages of the object
*@param typeBook - contains typeBook of the object
*/
public Novels(String nameOfBook, int nrPagesOfBook, String typeBook){
		super( nameOfBook, nrPagesOfBook);// call the base class constructor; must be first stament
		this.typeBook = typeBook;
	}


/**The method toString create a String which contains the stats of the object.
*@return - String which contains all the states of the object.
*/
	@Override
	public String toString()
	{
		return super.toString() + "\nBook type: " + typeBook; //call the toString method from super calss Books
	}
	
}