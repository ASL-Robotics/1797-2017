package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainBlueLeftCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeftGear extends CommandGroup {

    public AutoBlueLeftGear() {
    	addSequential(new DrivetrainBlueLeftCommand());
        addSequential(new FloorGearOuttakeCommand());
    }
}
