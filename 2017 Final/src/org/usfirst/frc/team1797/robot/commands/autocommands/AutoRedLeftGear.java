package org.usfirst.frc.team1797.robot.commands.autocommands;

import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.autoprofiles.DrivetrainRedLeftCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedLeftGear extends CommandGroup {

    public AutoRedLeftGear() {
        addSequential(new DrivetrainRedLeftCommand());
        addSequential(new FloorGearOuttakeCommand());
    }
}
