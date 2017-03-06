package org.usfirst.frc.team1797.robot.commands.autocommandgroups;

import org.usfirst.frc.team1797.robot.commands.DrivetrainRedRightCommand;
import org.usfirst.frc.team1797.robot.commands.PlaceSlotGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightGear extends CommandGroup {

    public AutoRightGear() {
    	addSequential(new DrivetrainRedRightCommand());
        addSequential(new PlaceSlotGear());
    }
}
