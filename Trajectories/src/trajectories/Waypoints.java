package trajectories;

import jaci.pathfinder.Trajectory.Config;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.Waypoint;

public class Waypoints {

	public static Waypoint AW_1 = new Waypoint(15.0, 269, 0);
	public static Waypoint AS_1 = new Waypoint(107, 238, -Math.PI / 3);
	public static Waypoint[] RED_1 = { AW_1, AS_1 };

	public static Waypoint AW_2 = new Waypoint(15.0, 162, 0);
	public static Waypoint AS_2 = new Waypoint(64.1, 162, 0);
	public static Waypoint[] RED_2 = { AW_2, AS_2 };

	public static Waypoint AW_3 = new Waypoint(15.0, 53.3, 0);
	public static Waypoint AS_3 = new Waypoint(107, 86.7, Math.PI / 3);
	public static Waypoint[] RED_3 = { AW_3, AS_3};

	public static Waypoint[][] AUTO_PAIRS = { RED_1, RED_2, RED_3};

	public static Config config = new Config(FitMethod.HERMITE_CUBIC, Config.SAMPLES_HIGH, 0.02, 127, 26.8, 139);
}