package simulation;

public class Triple
{
	double t;
	circle c1;
	circle c2;
	lineEquation lineEq;
	boolean wallCollision = false;
	public double getT() {
		return t;
	}
	public void setT(double t) {
		this.t = t;
	}
	public circle getC1() {
		return c1;
	}
	public void setC1(circle c1) {
		this.c1 = c1;
	}
	public circle getC2() {
		return c2;
	}
	public void setC2(circle c2) {
		this.c2 = c2;
	}
	public lineEquation getLineEq() {
		return lineEq;
	}
	public void setLineEq(lineEquation lineEq) {
		this.lineEq = lineEq;
	}
	public boolean isWallCollision() {
		return wallCollision;
	}
	public void setWallCollision(boolean wallCollision) {
		this.wallCollision = wallCollision;
	}

}
