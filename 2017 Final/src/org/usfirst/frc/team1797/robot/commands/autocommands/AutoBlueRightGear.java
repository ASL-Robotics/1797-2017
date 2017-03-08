package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainBlueRightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueRightGear extends CommandGroup {

    public AutoBlueRightGear() {
    	addSequential(new DrivetrainBlueRightCommand());
        addSequential(new FloorGearOuttakeCommand());
    }
}
