/**
 * 
 */
package ro.sci.h5;

import java.util.ArrayList;

/**
 * This class implements a container of shapes. It implements adding of shapes,
 * calculates the totalArea and totalPerimter.
 * 
 * @author ovidiu
 *
 */
public class ShapesContainer {
	private ArrayList<Shape> list = new ArrayList<Shape>();
	private double sumOfArea = 0;
	private double sumOfPerimeter = 0;

	/**
	 * This method add a Shape to the array list. It also add the perimeter and
	 * area of this shape to the corresponding variables sumOfArea and sumOfPerimeter.
	 * 
	 * @param string
	 *            - String containing the name of the shape in order to display
	 *            on the console.
	 * @param shape
	 *            -Shape to be added
	 */

	public boolean addShape(String string, Shape shape) {
		{

			for (Shape temp : list) {
				if (temp.equals(shape)) {
					System.out.println(string + " NOT added!");
					return false;
				}
			}
			list.add(shape);
			System.out.println(string + " added!");
			calculateTotalArea(shape.calculateArea());
			calculateTotalPerimeter(shape.calculatePerimeter());
			return true;

		}
	}

	/**
	 * Print total area and perimeter of the shapes on the console
	 */
	public void printTotalAreaAndPerimeter() {
		System.out.println("----------------");
		System.out.format("Nr of shapes added: %d%nTotal area is: %.2f%nTotal perimeter is: %.2f%n",
				list.size(),sumOfArea, sumOfPerimeter);//display only 2 decimals from sumofArea and sumOfPerimeter
	}

	/**
	 * Calculate total area of shapes
	 * 
	 * @param area
	 */
	public void calculateTotalArea(double area) {
		sumOfArea += area;
	}

	/**
	 * Calculate total perimeter of shapes
	 * 
	 * @param perimeter
	 */
	public void calculateTotalPerimeter(double perimeter) {
		sumOfPerimeter += perimeter;
	}

}
