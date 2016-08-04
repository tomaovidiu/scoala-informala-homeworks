package ro.sci.h5;

/**
 * This class is the main class of the application.
 * 
 * @author ovidiu
 *
 */
public class Main {

	public static void main(String[] args) {

		ShapesContainer shapesContainer = new ShapesContainer();

		// add rectangle
		shapesContainer.addShape("Rectangle(13, 3)", new Rectangle(13, 3));

		// add square
		shapesContainer.addShape("Square(9)", new Square(9));

		// add circle
		shapesContainer.addShape("Circle(10)", new Circle(10));

		// add triangle
		shapesContainer.addShape("Triangle(1, 2, 3)", new Triangle(1, 2, 3));

		// try to add another square [9] - generate error; it's already added
		shapesContainer.addShape("Square(9)", new Square(9));

		// try to add another square [10] - ok!
		shapesContainer.addShape("Square(10)", new Square(10));

		//print total area and total perimeter of shapes
		shapesContainer.printTotalAreaAndPerimeter();

	}
}
