/**
The class  Artalbumes is derived from the Book class. This class is a template for ArtAlbums objects. 
It has 1 method and 1 constuctor.
*/

public class ArtAlbums extends Book { // subclass of Book; ArtAlbums is a derived class
protected String paperQualityBook;

/**
The constructor of the class has 3 parameters:
*@param nameOfBook - contains the name of the object ArtAlbums which wil be created.
*@param nrPagesOfBook - contains the number of pages of the object
*@param paperQualityBook - contains paper quality book of the object
*/
public ArtAlbums(String nameOfBook, int nrPagesOfBook, String paperQualityBook){
		super( nameOfBook, nrPagesOfBook);// call the base class constructor; must be first stament
		this.paperQualityBook = paperQualityBook;
	}


/**The method toString create a String which contains the stats of the object.
*@return - String which contains all the states of the object.
*/
	@Override
	public String toString()
	{
		return super.toString() + "\nPaper Quality Book: " + paperQualityBook; //call the toString method from super calss Books
	}
	
}	