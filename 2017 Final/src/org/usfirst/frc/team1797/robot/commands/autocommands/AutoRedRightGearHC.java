package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainRedRightHCCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainRedRightReverseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedRightGearHC extends CommandGroup {

    public AutoRedRightGearHC() {
		addSequential(new AutoRedRightGear());
		addSequential(new DrivetrainRedRightReverseCommand());
		addSequential(new DrivetrainRedRightHCCommand());
    }
}
