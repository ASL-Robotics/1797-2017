package org.usfirst.frc.team1797.util;

import java.io.File;

public class FileTest {
	public static void main(String[] args){
		File left = new File("src\\trajectories\\station"+1+"_left.csv");
		System.out.println(left.exists());
	}
}
