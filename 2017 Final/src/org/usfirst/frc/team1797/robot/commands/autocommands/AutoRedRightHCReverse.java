package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainRedRightHCReverseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedRightHCReverse extends CommandGroup {

    public AutoRedRightHCReverse() {
    	addParallel(new FloorGearOuttakeCommand());
        addSequential(new DrivetrainRedRightHCReverseCommand());
    }
}
