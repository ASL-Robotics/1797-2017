package trajectories;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class Waypoints {

	// aw - alliance wall
	public static Waypoint RED_AW_1, RED_AW_2, BLUE_AW_1, BLUE_AW_2;

	// as - airshop
	public static Waypoint RED_AS_1, RED_AS_2, RED_AS_3, BLUE_AS_1, BLUE_AS_2, BLUE_AS_3;

	// o - overflow, r - return, ls - loading station
	public static Waypoint RED_OLS, RED_RLS, BLUE_OLS, BLUE_RLS;

	static {
		RED_AW_1 = new Waypoint(15, 53.3, Math.toRadians(-90));
		RED_AW_2 = new Waypoint(15, 268.8, Math.toRadians(-90));

		RED_AS_1 = new Waypoint(107.4, 86.71, Math.toRadians(-120));
	}

	public static void main(String[] args) {
		Waypoint[] points = new Waypoint[] { RED_AW_1, RED_AS_2 };
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
				Trajectory.Config.SAMPLES_HIGH, 0.02, 1.7, 2.0, 60);

		Trajectory traj = Pathfinder.generate(points, config);

		for (int i = 0; i < traj.length(); i++) {
			Trajectory.Segment seg = traj.get(i);

			System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n", seg.dt, seg.x, seg.y, seg.position, seg.velocity,
					seg.acceleration, seg.jerk, seg.heading);
		}
	}
}
