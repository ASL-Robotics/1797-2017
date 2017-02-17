package org.usfirst.frc.team1797.robot.commands.autocommandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStation1Baseline extends CommandGroup {

	public AutoStation1Baseline() {
		addSequential(new AutoStation1Stay());
	}
}
