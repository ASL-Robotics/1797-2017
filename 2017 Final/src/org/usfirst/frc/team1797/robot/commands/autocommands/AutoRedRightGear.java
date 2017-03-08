package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainRedRightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedRightGear extends CommandGroup {

    public AutoRedRightGear() {
    	addSequential(new DrivetrainRedRightCommand());
    	addSequential(new FloorGearOuttakeCommand());
    }
}
