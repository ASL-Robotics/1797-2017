package org.usfirst.frc.team1797.robot.commands.autocommandgroups;

import org.usfirst.frc.team1797.robot.commands.DrivetrainCenterCommand;
import org.usfirst.frc.team1797.robot.commands.PlaceSlotGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterGear extends CommandGroup {

    public AutoCenterGear() {
    	addSequential(new DrivetrainCenterCommand());
        addSequential(new PlaceSlotGear());
    }
}
