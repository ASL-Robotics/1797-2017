package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainRedLeftReverseCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainStraightHCCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedLeftGearHC extends CommandGroup {

    public AutoRedLeftGearHC() {
		addSequential(new AutoRedLeftGear());
		addSequential(new DrivetrainRedLeftReverseCommand());
		addSequential(new DrivetrainStraightHCCommand());
    }
}
