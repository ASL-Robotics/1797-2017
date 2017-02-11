/**
 * 
 */
package org.usfirst.frc.team1797.vision;

/**
 * @author Will
 * @deprecated
 */
public class HSVRange {
	double hMin, hMax, sMin, sMax, vMin, vMax;
	
	public HSVRange() {
		this(0,0,0,0,0,0);
	}
	
	public HSVRange(double hMin, double hMax, double sMin, double sMax, double vMin, double vMax) {
		this.hMin = hMin;
		this.hMax = hMax;
		this.sMin = sMin;
		this.sMax = sMax;
		this.vMin = vMin;
		this.vMax = vMax;
	}
	
	public double[] getMinValues() {
		return new double[] {hMin, sMin, vMin};
	}
	
	public double[] getMaxValues() {
		return new double[] {hMax, sMax, vMax};
	}

	public double gethMin() {
		return hMin;
	}

	public void sethMin(double hMin) {
		this.hMin = hMin;
	}

	public double gethMax() {
		return hMax;
	}

	public void sethMax(double hMax) {
		this.hMax = hMax;
	}

	public double getsMin() {
		return sMin;
	}

	public void setsMin(double sMin) {
		this.sMin = sMin;
	}

	public double getsMax() {
		return sMax;
	}

	public void setsMax(double sMax) {
		this.sMax = sMax;
	}

	public double getvMin() {
		return vMin;
	}

	public void setvMin(double vMin) {
		this.vMin = vMin;
	}

	public double getvMax() {
		return vMax;
	}

	public void setvMax(double vMax) {
		this.vMax = vMax;
	}
}
