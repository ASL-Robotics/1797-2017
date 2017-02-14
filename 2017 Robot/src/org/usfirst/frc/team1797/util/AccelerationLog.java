package org.usfirst.frc.team1797.util;

import java.io.File;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AccelerationLog {
	public static File data;

	public static void main(String[] args) {
		while (true) {
			System.out.println(run());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@SuppressWarnings("resource")
	public static String run() {

		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("10.17.97.1");
		NetworkTable networktable = NetworkTable.getTable("Network Table");

		double time = networktable.getNumber("Time", -1);
		double left = networktable.getNumber("Left", -1);
		double right = networktable.getNumber("Right", -1);

		return time +","+left+","+right;

	}
}
