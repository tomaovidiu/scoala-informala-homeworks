package ro.sci.h5;

/**
 * Implementation of a circle. It implements interface {@link Shape}.
 * 
 * @author ovidiu
 *
 */
public class Circle implements Shape {

	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.h5.Shape#calculateArea()
	 */
	@Override
	public double calculateArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.h5.Shape#calculatePerimeter()
	 */
	@Override
	public double calculatePerimeter() {
		return 2 * Math.PI * radius;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

}
