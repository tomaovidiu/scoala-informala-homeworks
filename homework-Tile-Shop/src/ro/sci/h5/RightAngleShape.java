package ro.sci.h5;

/**
 * This class is the abstract class for Rectangle Class. It overrides 3 methods:
 * <ul>
 * <li>CalculateArea()
 * <li>CalculatePerimeter()
 * <li>Equals() (of the Object Class)
 * </ul>
 * 
 * <b>The {@link RightAngleShape} implements interface {@link Shape}
 * 
 * @author ovidiu
 *
 */
public abstract class RightAngleShape implements Shape {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.h5.Shape#calculateArea()
	 */
	private double lenght;
	private double width;

	public RightAngleShape(double lenght, double width) {
		this.lenght = lenght;
		this.width = width;
	}

	@Override
	public double calculateArea() {
		return lenght * width;

	}

	@Override
	public double calculatePerimeter() {
		return 2 * (lenght + width);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RightAngleShape other = (RightAngleShape) obj;
		if (Double.doubleToLongBits(lenght) != Double.doubleToLongBits(other.lenght))
			return false;
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
			return false;
		return true;
	}

}
