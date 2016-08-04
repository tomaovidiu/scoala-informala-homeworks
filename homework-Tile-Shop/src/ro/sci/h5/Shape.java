package ro.sci.h5;

/**
 * The interface defines two methods (with empty bodies) for calculation the
 * area, respectively perimeter of a shape.
 * 
 * @author ovidiu
 *
 */
public interface Shape {

	/**
	 * calculate area of a shape
	 * 
	 * @return the value of the area of the shape
	 * 
	 */
	public double calculateArea();

	/**
	 * calculate perimeter of a shape
	 * 
	 * @return the value of the perimeter of the shape
	 * 
	 */
	public double calculatePerimeter();

}
