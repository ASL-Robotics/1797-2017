package trajectories;

import java.io.File;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

public class Runner {
	public static void main(String[] args) {
		Trajectory[][] t = Trajectories.returnTankTraj(Trajectories.returnTraj(Waypoints.AUTO_PAIRS));
		for (int i = 0; i < t.length; i++) {
			Trajectory[] pair = t[i];
			File left = new File("left_"+Integer.toString(i)+".csv");
			File right = new File("right_"+Integer.toString(i)+".csv");
			Pathfinder.writeToCSV(left, pair[0]);
			Pathfinder.writeToCSV(right, pair[1]);
		}
	}
}
