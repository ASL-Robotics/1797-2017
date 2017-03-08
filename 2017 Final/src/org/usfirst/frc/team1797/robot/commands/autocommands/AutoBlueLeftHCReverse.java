package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainBlueLeftHCReverseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeftHCReverse extends CommandGroup {

    public AutoBlueLeftHCReverse() {
    	addParallel(new FloorGearOuttakeCommand());
        addSequential(new DrivetrainBlueLeftHCReverseCommand());
    }
}
