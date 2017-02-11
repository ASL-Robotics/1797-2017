package trajectories;

import java.io.File;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class Waypoints {

	public static void main(String[] args) {

		Waypoint RED_AW_1 = new Waypoint(15, 53.3, Math.toRadians(180));
		Waypoint RED_AS_1 = new Waypoint(107.4, 86.71, Math.toRadians(-120));

		Waypoint[] points = new Waypoint[] { RED_AW_1, RED_AS_1 };
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
				Trajectory.Config.SAMPLES_HIGH, 0.02, 120, 120, 120);

		Trajectory traj = Pathfinder.generate(points, config);

		File myFile = new File("myfile.csv");
		Pathfinder.writeToCSV(myFile, traj);
	}
}
