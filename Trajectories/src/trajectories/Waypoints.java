package trajectories;

import jaci.pathfinder.Trajectory.Config;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.Waypoint;

public class Waypoints {

	public static Waypoint RED_AW_1 = new Waypoint(14.8, 269, 0);
	public static Waypoint RED_AS_1 = new Waypoint(124, 206, -Math.PI / 3);
	public static Waypoint RED_AS_1_INVERTED = new Waypoint(124, 206, 2 * Math.PI / 3);
	public static Waypoint[] RED_LEFT = { RED_AW_1, RED_AS_1 };

	public static Waypoint[] RED_LEFT_REVERSE = { RED_AS_1, RED_AW_1 };

	public static Waypoint AW_2 = new Waypoint(0, 162, 0);
	public static Waypoint AS_2 = new Waypoint(83, 162, 0);
	public static Waypoint[] CENTER = { AW_2, AS_2 };

	public static Waypoint RED_AW_3 = new Waypoint(14.8, 52, 0);
	public static Waypoint RED_AS_3 = new Waypoint(124, 118, Math.PI / 3);
	public static Waypoint RED_AS_3_INVERTED = new Waypoint(124, 118, -2 * Math.PI / 3);

	public static Waypoint[] RED_RIGHT = { RED_AW_3, RED_AS_3 };

	public static Waypoint[] RED_RIGHT_REVERSE = { RED_AS_3, RED_AW_3 };

	public static Waypoint RED_BYPASS = new Waypoint(185, 52, 0);
	public static Waypoint RED_NEUTRAL = new Waypoint(326, 269, 0);
	public static Waypoint[] RED_LEFT_NEUTRAL = { RED_AW_1, RED_NEUTRAL };
	public static Waypoint[] RED_RIGHT_NEUTRAL = { RED_AW_3, RED_BYPASS, RED_NEUTRAL };

	public static Waypoint[][] AUTO_PAIRS = { RED_LEFT, CENTER, RED_RIGHT };

	public static Waypoint[][] NEUTRAL_PAIRS = { RED_LEFT_NEUTRAL, RED_RIGHT_NEUTRAL };

	public static Waypoint[][] REVERSE_PAIRS = { RED_LEFT_REVERSE, RED_RIGHT_REVERSE };

	public static Config config = new Config(FitMethod.HERMITE_CUBIC, Config.SAMPLES_HIGH, 0.02, 127, 65.1, 2200);
}