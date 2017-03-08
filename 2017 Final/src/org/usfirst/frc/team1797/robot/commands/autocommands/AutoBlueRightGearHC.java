package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainBlueRightReverseCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainStraightHCCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueRightGearHC extends CommandGroup {

	public AutoBlueRightGearHC() {
		addSequential(new AutoBlueRightGear());
		addSequential(new DrivetrainBlueRightReverseCommand());
		addSequential(new DrivetrainStraightHCCommand());
	}
}
