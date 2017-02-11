package org.usfirst.frc.team1797.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.networktables.*;

public class AccelerationLogger {

	static File data;

	public static void main(String[] args) {
		init();
		while (true) {
			run();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void init() {
		data = new File("accelTest");
		try {
			data.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	public static void run() {

		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("10.17.97.1");
		NetworkTable networktable = NetworkTable.getTable("Network Table");

		double time = networktable.getNumber("Time", -1);
		double x = networktable.getNumber("X Accel", -1);
		double y = networktable.getNumber("Y Accel", -1);
		double z = networktable.getNumber("Z Accel", -1);

		try {
			(new BufferedWriter(new FileWriter(data))).write(time + "\t" + x + "\t" + y + "\t" + z + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
