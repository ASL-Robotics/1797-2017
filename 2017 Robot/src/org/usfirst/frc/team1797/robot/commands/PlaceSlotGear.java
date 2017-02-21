package org.usfirst.frc.team1797.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceSlotGear extends CommandGroup {

    public PlaceSlotGear() {
    	addSequential(new DrivetrainAimCommand());
        addSequential(new DrivetrainDriveToWallCommand());
        addSequential(new FloorGearOuttakeCommand());
    }
}
