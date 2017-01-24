/**
 * 
 */
package org.usfirst.frc.team1797.util;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * @author Will
 * 
 * AnalogUltrasonicSensor is a wrapper class around the HRLV-MaxSonar-EZ series of ultrasonic sensors. It reads the
 * value in from an AnalogInput and then applies the proper scale factor when read.
 */
public class AnalogUltrasonicSensor {
	private AnalogInput analogIn;
	
	public AnalogInput getAnalogIn() {
		return analogIn;
	}


	/**
	 * @param port The analog in port that the ultrasonic sensor is plugged into
	 */
	public AnalogUltrasonicSensor(int port) {
		analogIn = new AnalogInput(port);
	}
	
	
	/**
	 * @return The number of inches away from the intended target
	 */
	public double inchesAway() {
		return analogIn.getVoltage() * 38.464566; // inches per volt
	}
}
