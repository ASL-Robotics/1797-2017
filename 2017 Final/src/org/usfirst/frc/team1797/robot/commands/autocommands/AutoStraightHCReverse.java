package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainStraightHCReverseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightHCReverse extends CommandGroup {

    public AutoStraightHCReverse() {
        addParallel(new FloorGearOuttakeCommand());
        addSequential(new DrivetrainStraightHCReverseCommand());
    }
}
