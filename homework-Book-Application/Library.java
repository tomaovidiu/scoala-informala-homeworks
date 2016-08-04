import java.util.*;

/* TASK
Create a small application that mimics a library catalog. 
It needs to be able to 
- add books, 
- delete books 
- list books(print on console the book's characteristics)
Books are of two kinds: novels and art albums. They both have names and number of pages. Novels have type (like mystery, sf, etc.) while albums have paper quality.
Model these entities (book, novel, album) with different classes and inheritance. 
*/

/** The Libray class helps to implement an application that mimics a library catalog. The application is able to:
<p>
*<b>The application is able to</b> 
*<ul> 
*<li> add books, 
*<li> delete books
*<li> list books
*</ul>
*<b>Books are of two kinds</b> 
*<ul> 
*<li> novels 
*<li> art albums
*</ul>
<p>
* They both have names and number of pages. Novels have type (like mystery, sf, etc.), while albums have paper quality.
<p>
* The library uses Book Objects - instances of Book Class. Book objects are stored in global variable "list", which is the type of array list.
*@author Ovi
*@since 2016-11-11
*@version 1.0
*/
public class Library{
	private static Scanner input = new Scanner(System.in); // the object for read the user's input
	private static char userChoice;  // input of the user
	private static int i; // used for generating diffent names of books
	protected static ArrayList<Book> list = new ArrayList<Book>(); // use to implement with array list because the dinamic size of list

/**
*This is the main method in which the user chooses the desired operation: Add book, Delete book, List book or exit the program. For each specific operation a specific method is called.
*@param args Unused.
*/
public static void main (String[] args) {
	Library lib = new Library(); // object l is created in order to be non static and exclusive use of OOP:)
	do { // display the menu until the user will press "0" for exit the program
			lib.displayMenu();
			userChoice = input.next().charAt(0); //read the first char input
			System.out.println("---------------------------------");
			switch (userChoice){
				case '1': {
					System.out.println("Enter the kind of the book you want to add: \n-> 1 - Novels \n-> Any other key - Art albums");
					System.out.print("Enter choice:");
					input.nextLine();
					//Depending on the kind of the book 
					if (input.next().charAt(0)=='1') {
						lib.addBook(new Novels("Novels" + ++i, 100 + i, "SF"));// add object to the list book
					} else {
						lib.addBook(new ArtAlbums("ArtAlbums" + ++i, 200 + i, "very good"));// add object to the list book
						//create ArtAlbums with contructor; i is used for generating diferent names, nr of pages etc
					}
					System.out.println("The book added!");
					break;
				}
		
				case '2': {
					if (list.size() == 0) System.out.print("Is no book in the library!");
					else {
					System.out.print("Enter the book name you want to delete:");
					input.nextLine(); // read the name
					System.out.println(( lib.deleteBook(input.nextLine()) ) ? "The book was deleted!" : "The book was NOT found!"); // call the method delete book and print the results of action to delete
					}
					break;
				}
				
				case '3': {
					if (list.size() == 0) {
						System.err.println("Error! The number of books =0!");
					}
					else {
						System.out.println("List of books [total "+list.size() +"]:");
						System.out.println(lib.listBook());
					}
					break;
				}
				
				default: {
					if (userChoice != '0') {
						System.out.println("Please enter a valid choice!");
						break;
					}
				}
			}			
		} while (userChoice != '0');
	}

/**
*This method display user menu. It's called from main method.
*/
public void displayMenu() {
	System.out.println("---------------------------------");
	System.out.println("-> 1 - Add Book");
	System.out.println("-> 2 - Delete Book");
	System.out.println("-> 3 - List Books; Number of books = " + list.size());
	System.out.println("-> 0 - Exit");
	System.out.print("Enter choice:");
}

/**
*This method add a book to the libray. It's called from main method. 
*@param b - the book to be add to the library
*/
public void addBook(Book b) {
	list.add(b); // add object b to the array list
}

/**
*This method delete a book from the libray. It's called from main method. The user enter the name of the book, and  after that the book with this name will be deleted. T
Only the first book will be deleted.
*@param bookNameToDelete - book name to delete.
*@return - the status of finding the book name. Is {@code true} when a book is succesfully deleted.
*/
public boolean deleteBook(String bookNameToDelete) {
	for (int i=0; i< list.size(); i++){
		if (bookNameToDelete.equals(list.get(i).nameOfBook)) {
		list.remove(i);
		return true;// exit for at first found of the nameOfBook
		} 
	}
	return false; // if the book not found return false
}

/**
*This method creates a String which contais the states of all the books from the libray, using method toString.
*It overrides the toString method of class Object.
*@return - String - contains all the states of all the objects from the list.
*/
public String listBook() {
   	String temp = " ";
	for (int i=0; i< list.size(); i++){
		temp = temp + list.get(i).toString() + "\n ----";
		}
	return temp;
}
}