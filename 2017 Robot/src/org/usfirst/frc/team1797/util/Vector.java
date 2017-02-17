package org.usfirst.frc.team1797.util;

public class Vector {
	private double r;
	private double phi;

	public Vector(double r, double phi) {
		this.r = r;
		this.phi = phi;
	}

	public double getX() {
		return r * Math.sin(Math.toRadians(phi));
	}

	public double getY() {
		return r * Math.cos(Math.toRadians(phi));
	}

	public static Vector add(Vector u, Vector v) {

		double x = u.getX() + v.getX(), y = u.getY() + v.getY();

		double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		double phi = Math.toDegrees(Math.atan(x / y));

		return (new Vector(r, phi));

	}
	
	public double getR() {
		return r;
	}

	public double getPhi() {
		return phi;
	}
}
