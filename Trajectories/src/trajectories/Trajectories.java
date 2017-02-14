package trajectories;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Trajectories {

	public static Trajectory[] returnTraj(Waypoint[][] pairs) {
		Trajectory[] traj = new Trajectory[pairs.length];
		for (int i = 0; i < traj.length; i++)
			traj[i] = Pathfinder.generate(pairs[i], Waypoints.config);
		return traj;
	}

	public static Trajectory[][] returnTankTraj(Trajectory[] traj) {
		Trajectory[][] tankTraj = new Trajectory[traj.length][2];
		for (int i = 0; i < traj.length; i++) {
			TankModifier mod = new TankModifier(traj[i]);
			mod.modify(23);
			Trajectory[] pair = new Trajectory[2];
			pair[0] = mod.getLeftTrajectory();
			pair[1] = mod.getRightTrajectory();
			tankTraj[i] = pair;
		}
		return tankTraj;
	}
	
	
}
