package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainCenterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterGear extends CommandGroup {

    public AutoCenterGear() {
    	addSequential(new DrivetrainCenterCommand());
    	addSequential(new FloorGearOuttakeCommand());
    }
}
