package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainBlueLeftHCCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainBlueLeftReverseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeftGearHC extends CommandGroup {

    public AutoBlueLeftGearHC() {
		addSequential(new AutoBlueLeftGear());
		addSequential(new DrivetrainBlueLeftReverseCommand());
		addSequential(new DrivetrainBlueLeftHCCommand());
    }
}
