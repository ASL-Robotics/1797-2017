/**
 * 
 */
package org.usfirst.frc.team1797.util;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * @author Will
 * 
 *         AnalogUltrasonicSensor is a wrapper class around the HRLV-MaxSonar-EZ
 *         series of ultrasonic sensors. It reads the value in from an
 *         AnalogInput and then applies the proper scale factor when read.
 */
public class Ultrasonic extends AnalogInput{

	/**
	 * @param port
	 *            The analog in port that the ultrasonic sensor is plugged into
	 */
	public Ultrasonic(int channel) {
		super(channel);
	}

	/**
	 * @return The number of inches away from the intended target
	 */
	public double getDistance() {
		return getVoltage() * 38.464566; // inches per volt
	}
}
