package org.usfirst.frc.team1797.udp.transmission;

@Deprecated
public class VisionInformation {
	int x1, y1, x2, y2;
	byte numTargetsFound;
	
	public VisionInformation(String info) {
		String[] numbers = info.split(",");
		System.out.println(info.length());
		
		if (numbers.length == 4) {
			numTargetsFound = 2;
			x1 = Integer.parseInt(numbers[0]);
			y1 = Integer.parseInt(numbers[1]);
			x2 = Integer.parseInt(numbers[2]);
			y2 = Integer.parseInt(numbers[3]);
		} else if (numbers.length == 2) {
			numTargetsFound = 1;
			x1 = Integer.parseInt(numbers[0]);
			y1 = Integer.parseInt(numbers[1]);
			x2 = -1;
			y2 = -1;
		} else {
			numTargetsFound = 0;
			x1 = -1;
			y1 = -1;
			x2 = -1;
			y2 = -1;
		}
	}
}
